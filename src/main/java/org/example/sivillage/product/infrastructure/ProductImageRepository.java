package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Optional<ProductImage> findByProductCodeAndThumbnailTrue(String productCode);
}
