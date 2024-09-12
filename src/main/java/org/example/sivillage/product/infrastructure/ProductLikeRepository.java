package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {

    Integer countByProductCode(String productCode);

    Optional<ProductLike> findByProductCodeAndMemberUuid(String productCode, String memberUuid);

}