package org.example.sivillage.domain.product.infrastructure;

import org.example.sivillage.domain.product.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

}
