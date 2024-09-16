package org.example.sivillage.product.infrastructure;

import org.example.sivillage.vendor.domain.ProductOptionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOptionList, Long> {

    List<ProductOptionList> findByProductCode(String productCode);
}
