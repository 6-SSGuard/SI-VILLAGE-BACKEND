package org.example.sivillage.domain.brand.infrastructure;

import jakarta.validation.constraints.NotBlank;
import org.example.sivillage.domain.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByBrandName(@NotBlank(message = "브랜드 이름은 필수 값입니다.") String brandName);
}
