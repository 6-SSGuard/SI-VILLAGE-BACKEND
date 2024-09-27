package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.product.domain.ProductLike;
import org.example.sivillage.product.domain.ProductLikeCount;
import org.example.sivillage.product.dto.in.GetLikeInfoRequestDto;
import org.example.sivillage.product.dto.in.ProductLikeCountRequestDto;
import org.example.sivillage.product.dto.out.GetLikeCountResponseDto;
import org.example.sivillage.product.infrastructure.ProductLikeCountRepository;
import org.example.sivillage.product.infrastructure.ProductLikeRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String redisKey = productCode + ":" + memberUuid + ":likes"; // 좋아요 상태 key
        String countKey = "product:" + productCode + ":likeCount"; // 좋아요 수 key

        Boolean isLiked = redisTemplate.opsForSet().isMember(redisKey, memberUuid);

        if (isLiked != null && isLiked) {
            // 이미 좋아요 눌렀을 경우
            redisTemplate.opsForValue().decrement(countKey, 1); // 좋아요 수 감소
            redisTemplate.opsForSet().remove(redisKey, memberUuid); // Redis에서 삭제
        } else {
            // 좋아요를 누르지 않은 경우
            redisTemplate.opsForValue().increment(countKey, 1); // 좋아요 수 증가
            redisTemplate.opsForSet().add(redisKey, memberUuid); // Redis에 추가
        }
    }

    @Scheduled(fixedRate = 10000)
    public void addProductLikeCount() {
        String keyPattern = "product:*:likeCount"; // 수정된 부분
        Set<String> keys = redisTemplate.keys(keyPattern);

        if (keys != null) {
            keys.forEach(key -> {
                String likeCountStr = redisTemplate.opsForValue().get(key); // 변경된 부분
                String likeProductCode = key.split(":")[1]; // productCode

                if (likeCountStr != null) {
                    Long likeCount = Long.valueOf(likeCountStr); // 좋아요 수 변환

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
    }

    @Scheduled(fixedRate = 10000)
    public void addProductLikeStatus() {
        Set<String> keys = redisTemplate.keys("*:likes");

        if (keys != null) {
            for (String key : keys) {
                String[] keyParts = key.split(":");

                if (keyParts.length == 3) {
                    String productCode = keyParts[0];
                    String memberUuid = keyParts[1];

                    Boolean likeStatus = redisTemplate.opsForSet().isMember(key, memberUuid);
                    ProductLike productLike = productLikeRepository.findByProductCodeAndMemberUuid(productCode, memberUuid).orElse(null);

                    if (likeStatus != null) {
                        boolean isLiked = likeStatus; // Redis에서 좋아요 상태를 boolean으로 저장

                        // DB에 상태 업데이트
                        if (productLike == null) {
                            // DB에 해당 값이 없다면 새로 생성
                            productLikeRepository.save(GetLikeInfoRequestDto.toEntity(productCode, memberUuid, isLiked));
                        } else {
                            // DB에 이미 값이 존재하는 경우, 상태를 업데이트
                            productLike.toggleLike();
                            productLikeRepository.save(productLike);
                        }
                    }

                }
            }
        }
    }

    @Override
    public GetLikeInfoResponseDto getLikeInfo(String productCode, String memberUuid) {
        String redisKey = productCode + ":" + memberUuid + ":likes";
        Boolean isLiked = redisTemplate.opsForSet().isMember(redisKey, memberUuid);
        return GetLikeInfoResponseDto.from(isLiked != null && isLiked);
    }
}
