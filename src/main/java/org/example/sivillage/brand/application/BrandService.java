package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Brand createBrand(String brandName) {
        return brandRepository.findByBrandName(brandName)
                .orElseGet(() -> {
                    Brand brand = Brand.createBrand(brandName);
                    return brandRepository.save(brand);
                });
    }
}
