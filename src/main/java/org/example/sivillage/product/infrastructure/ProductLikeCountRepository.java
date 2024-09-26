package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductLikeCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductLikeCountRepository extends JpaRepository<ProductLikeCount,Long> {
    Optional<ProductLikeCount> findByProductCode(String likeProductCode);
    Integer countByProductCode(String productCode);
}
