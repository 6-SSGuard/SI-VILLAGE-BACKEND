package org.example.sivillage.product.application;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.product.dto.in.ProductViewCountRequestDto;
import org.example.sivillage.product.infrastructure.ProductViewCountRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductViewCountServiceImpl implements ProductViewCountService {
    private final RedisTemplate<String, Long> redisTemplate;
    private final ProductViewCountRepository productViewCountRepository;


    public void incrementViewProduct(String productCode) {
        log.info("Incrementing view count for product: {}", productCode);
        redisTemplate.opsForValue().increment("productViewCount:" + productCode, 1); // 조회수 1 증가

    }


    @Scheduled(fixedRate = 1000) // 10초 마다 db에 저장
    public void addProductViewCount() {

        String keyPattern = "productViewCount:*";
        Set<String> keys = redisTemplate.keys(keyPattern);

        if (keys != null) {

            keys.forEach(key -> {
                Long viewCount = redisTemplate.opsForValue().get(key);
                if (viewCount != null) {
                    String productCode = key.replace("productViewCount:", "");
                    productViewCountRepository.save(ProductViewCountRequestDto.toEntity(productCode, viewCount));
                }
            });
        }
    }
}



