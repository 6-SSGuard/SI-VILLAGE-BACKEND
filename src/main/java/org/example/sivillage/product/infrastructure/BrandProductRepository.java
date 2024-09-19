package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.BrandProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandProductRepository extends JpaRepository<BrandProduct, Long> {
    Optional<BrandProduct> findByProductCode(String productCode);
}
