package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    Optional<ProductOption> findByProductCode(String productCode);
}
