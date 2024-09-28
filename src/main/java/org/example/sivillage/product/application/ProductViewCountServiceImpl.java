package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.product.domain.ProductViewCount;
import org.example.sivillage.product.dto.in.ProductViewCountRequestDto;
import org.example.sivillage.product.infrastructure.ProductViewCountRepository;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductViewCountServiceImpl implements ProductViewCountService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ProductViewCountRepository productViewCountRepository;

    public void incrementViewProduct(String productCode) {
        String lockKey = "lock:productViewCount:" + productCode; // Lock key 생성
        Boolean lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, "locked", 10, TimeUnit.SECONDS); // 10초 유효시간 설정

        if (Boolean.TRUE.equals(lockAcquired)) {
            try {
                // Redis에서 조회수를 1 증가
                redisTemplate.opsForValue().increment("productViewCount:" + productCode, 1);
            } finally {
                redisTemplate.delete(lockKey); // 작업 완료 후 잠금 해제
            }
        } else {
            log.warn("Lock not acquired for productCode: {}", productCode);
        }
    }

    @Scheduled(fixedRate = 10000)
    public void addProductViewCount() {
        String keyPattern = "productViewCount:*";
        Set<String> keys = redisTemplate.keys(keyPattern);

        if (keys != null && !keys.isEmpty()) {
            // Redis에서 조회수 값을 한 번에 가져오기
            Map<String, String> viewCountMap = new HashMap<>();
            redisTemplate.executePipelined(new SessionCallback<Object>() {

                @Override
                public Object execute(RedisOperations operations) {
                    keys.forEach(key -> {
                        operations.opsForValue().get(key); // 조회수 가져오기
                    });
                    return null;
                }
            });

            List<ProductViewCount> productViewCountsToSave = new ArrayList<>();

            // JPA로 DB에 저장
            for (String key : keys) {
                String viewCountStr = redisTemplate.opsForValue().get(key);
                if (viewCountStr != null) {
                    String productCode = key.replace("productViewCount:", "");
                    Long count = Long.valueOf(viewCountStr);

                    Optional<ProductViewCount> existingViewCount = productViewCountRepository.findByProductCode(productCode);

                    if (existingViewCount.isPresent()) {
                        // 기존 카운트를 업데이트
                        ProductViewCount updatedViewCount = existingViewCount.get();
                        ProductViewCountRequestDto.updateToEntity(updatedViewCount,count); // 카운트 업데이트
                        productViewCountsToSave.add(updatedViewCount); // 리스트에 추가
                    } else {
                        // 새로운 엔티티 저장
                        productViewCountsToSave.add(ProductViewCountRequestDto.toEntity(productCode, count)); // 리스트에 추가
                    }
                }
            }

            // JPA의 saveAll로 일괄 저장
            if (!productViewCountsToSave.isEmpty()) {
                productViewCountRepository.saveAll(productViewCountsToSave);
            }
        }
    }
}
