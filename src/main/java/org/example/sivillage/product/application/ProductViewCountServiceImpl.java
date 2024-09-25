package org.example.sivillage.product.application;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.product.dto.in.ProductViewCountRequestDto;
import org.example.sivillage.product.infrastructure.ProductViewCountRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional

public class ProductViewCountServiceImpl implements ProductViewCountService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ProductViewCountRepository productViewCountRepository;

    // 상품 조회수 증가
    public void incrementViewProduct(String productCode) {
        String key = "product:viewCount";
        redisTemplate.opsForZSet().incrementScore(key, productCode, 1);
    }


    @Scheduled(fixedRate = 1000) // 10초 마다 db에 저장
    public void addProductViewCount() {

        String key = "product:viewCount";
        Set<Object> productViews = redisTemplate.opsForZSet().range(key, 0, -1); // 모든 상품 코드 가져오기

        if (productViews != null) {
            productViews.forEach(productCodeObj -> {
                String productCode = (String) productCodeObj;
                Double viewCount = redisTemplate.opsForZSet().score(key, productCode);
                productViewCountRepository.save(ProductViewCountRequestDto.toEntity(productCode, viewCount)); //
            });
        }
    }
}



