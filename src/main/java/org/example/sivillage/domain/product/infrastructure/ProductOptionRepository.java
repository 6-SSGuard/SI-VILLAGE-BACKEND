package org.example.sivillage.domain.product.infrastructure;

import org.example.sivillage.domain.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductOption, Long> {

}
