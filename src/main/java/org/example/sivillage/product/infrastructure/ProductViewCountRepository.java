package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductViewCountRepository extends JpaRepository<ProductViewCount,Long> {

    Optional<ProductViewCount> findByProductCode(String productCode);
}
