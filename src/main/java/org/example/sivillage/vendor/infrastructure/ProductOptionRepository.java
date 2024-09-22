package org.example.sivillage.vendor.infrastructure;

import org.example.sivillage.vendor.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    List<ProductOption> findByProductCode(String productCode);
}
