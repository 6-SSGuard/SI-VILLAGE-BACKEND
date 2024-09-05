package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductCode(String productCode);
}
