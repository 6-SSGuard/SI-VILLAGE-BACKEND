package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    List<ProductOption> findByProductCode(String productCode);
}
