package org.example.sivillage.product.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.product.domain.ProductViewCount;
import org.example.sivillage.product.dto.in.ProductViewCountRequestDto;
import org.example.sivillage.product.infrastructure.ProductViewCountRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductViewCountServiceImpl implements ProductViewCountService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ProductViewCountRepository productViewCountRepository;

    public void incrementViewProduct(String productCode) {
        redisTemplate.opsForValue().increment("productViewCount:" + productCode, 1); // 조회수 1 증가
    }

    @Scheduled(fixedRate = 10000)
    public void addProductViewCount() {
        String keyPattern = "productViewCount:*";
        Set<String> keys = redisTemplate.keys(keyPattern);

        if (keys != null) {
            keys.forEach(key -> {
                String viewCount = redisTemplate.opsForValue().get(key);
                if (viewCount != null) {
                    String productCode = key.replace("productViewCount:", "");

                    Optional<ProductViewCount> existingViewCount = productViewCountRepository.findByProductCode(productCode);

                    existingViewCount.ifPresent(productViewCount -> {
                        productViewCountRepository.save(ProductViewCountRequestDto.updateToEntity(productViewCount, Long.valueOf(viewCount)));
                    });

                    if (existingViewCount.isEmpty()) {
                        productViewCountRepository.save(ProductViewCountRequestDto.toEntity(productCode, Long.valueOf(viewCount)));
                    }
                }
            });
        }
    }
}
