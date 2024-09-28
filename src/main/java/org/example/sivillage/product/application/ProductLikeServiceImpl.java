package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.product.domain.ProductLike;
import org.example.sivillage.product.domain.ProductLikeCount;
import org.example.sivillage.product.dto.in.GetLikeInfoRequestDto;
import org.example.sivillage.product.dto.in.ProductLikeCountRequestDto;
import org.example.sivillage.product.dto.out.GetLikeCountResponseDto;
import org.example.sivillage.product.infrastructure.ProductLikeCountRepository;
import org.example.sivillage.product.infrastructure.ProductLikeRepository;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLikeServiceImpl implements ProductLikeService {

    private final ProductLikeCountRepository productLikeCountRepository;
    private final ProductLikeRepository productLikeRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional(readOnly = true)
    public GetLikeCountResponseDto getLikeCount(String productCode) {
        String countKey = "product:" + productCode + ":likeCount";
        String likeCountStr = redisTemplate.opsForValue().get(countKey);
        return GetLikeCountResponseDto.from(likeCountStr != null ? Long.parseLong(likeCountStr) : 0L);
    }

    @Override
    public void toggleProductLike(String productCode, String memberUuid) {
        String redisKey = "like:product:" + productCode + ":member:" + memberUuid + ":likes"; // 좋아요 상태 key
        String countKey = "product:" + productCode + ":likeCount"; // 좋아요 수 key
        String dirtyFlagKey = "like:product:" + productCode + ":member:" + memberUuid + ":dirty"; // 상태 변경 플래그

        Boolean isLiked = redisTemplate.opsForSet().isMember(redisKey, memberUuid);

        if (isLiked != null && isLiked) {
            // 이미 좋아요 눌렀을 경우
            redisTemplate.opsForValue().decrement(countKey, 1); // 좋아요 수 감소
            redisTemplate.opsForSet().remove(redisKey, memberUuid); // Redis에서 삭제
            redisTemplate.opsForValue().set(dirtyFlagKey, "true"); // 상태 변경 플래그 설정
            System.out.println("좋아요 상태 해제 - Redis에서 삭제됨");
        } else {
            
            // 좋아요를 누르지 않은 경우
            redisTemplate.opsForValue().increment(countKey, 1); // 좋아요 수 증가
            redisTemplate.opsForSet().add(redisKey, memberUuid); // Redis에 추가
            //          redisTemplate.opsForValue().set(dirtyFlagKey, "true"); // 상태 변경 플래그 설정
            System.out.println("좋아요 상태 추가 - Redis에 저장됨");
        }

        // 상태 확인을 위해 로그 추가
        Boolean checkLikeStatus = redisTemplate.opsForSet().isMember(redisKey, memberUuid);
        System.out.println("Redis에 저장된 좋아요 상태: " + checkLikeStatus);
    }

    // Schedule this method to run periodically
    @Scheduled(fixedRate = 10000)
    public void addProductLikeCount() {
        String keyPattern = "product:*:likeCount";
        Set<String> keys = redisTemplate.keys(keyPattern);

        if (keys != null) {
            keys.forEach(key -> {
                String likeCountStr = redisTemplate.opsForValue().get(key);
                String likeProductCode = key.split(":")[1];

                if (likeCountStr != null) {
                    Long likeCount = Long.valueOf(likeCountStr);

                    Optional<ProductLikeCount> existingProductLikeCount = productLikeCountRepository.findByProductCode(likeProductCode);

                    if (existingProductLikeCount.isPresent()) {
                        // 이미 존재하는 경우 업데이트
                        productLikeCountRepository.save(ProductLikeCountRequestDto.updateToEntity(existingProductLikeCount.get(), likeCount));
                    } else {
                        // 새로운 경우 추가
                        productLikeCountRepository.save(ProductLikeCountRequestDto.toEntity(likeProductCode, likeCount));
                    }
                }
            });
        }

        // Synchronize after updating like counts
        syncProductLikeWithDB();
    }

    @Transactional
    public void syncProductLikeWithDB() {
        Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(
                ScanOptions.scanOptions().match("*:likes").count(1000).build());

        while (cursor.hasNext()) {
            String key = new String(cursor.next(), StandardCharsets.UTF_8);
            System.out.println("현재 처리 중인 Redis 키: " + key);


            String[] keyParts = key.split(":");
            if (keyParts.length == 6) {
                String productCode = keyParts[2];
                String memberUuid = keyParts[4];
                String dirtyFlagKey = "like:product:" + productCode + ":member:" + memberUuid + ":dirty";
                System.out.println("조회할 더티 플래그 키: " + dirtyFlagKey);

                // 더티 플래그 조회
                String isDirty = redisTemplate.opsForValue().get(dirtyFlagKey);
                System.out.println("조회된 더티 플래그 값: " + isDirty);

                // 더티 플래그가 존재하는 경우
                if ("true".equals(isDirty)) {
                    Boolean likeStatus = redisTemplate.opsForSet().isMember(key, memberUuid);
                    ProductLike productLike = productLikeRepository.findByProductCodeAndMemberUuid(productCode, memberUuid).orElse(null);

                    // 좋아요 상태를 DB에 반영
                    if (likeStatus != null) {
                        if (productLike == null) {
                            // 좋아요를 처음 눌렀을 경우, DB에 새로 저장
                            System.out.println("DB에 새로 저장: " + productCode + ", " + memberUuid);
                            productLikeRepository.save(GetLikeInfoRequestDto.toEntity(productCode, memberUuid, true));
                        } else {
                            // 좋아요를 취소할 경우, 기존 좋아요 상태를 토글하여 업데이트
                            System.out.println("DB에 업데이트: " + productCode + ", " + memberUuid);
                            productLike.toggleLike();
                            productLikeRepository.save(productLike);
                        }
                        // 동기화 후 플래그 삭제
                        redisTemplate.delete(dirtyFlagKey);
                    }
                } else {
                    // 더티 플래그가 없고 DB에 좋아요 정보가 있는 경우
                    ProductLike productLike = productLikeRepository.findByProductCodeAndMemberUuid(productCode, memberUuid).orElse(null);
                    if (productLike != null) {
                        System.out.println("변경 사항 없음, DB와 일치합니다: " + productCode + ", " + memberUuid);
                    } else {
                        // DB에 좋아요 정보가 없는 경우, 처음 저장하는 로직
                        System.out.println("DB에 새로 저장할 좋아요 정보: " + productCode + ", " + memberUuid);
                        productLikeRepository.save(GetLikeInfoRequestDto.toEntity(productCode, memberUuid, true));
                    }
                }
            }
        }
    }
}


