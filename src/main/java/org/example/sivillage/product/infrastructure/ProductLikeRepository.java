package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {

    Integer countByProductCode(String productCode);

    Optional<ProductLike> findByProductCodeAndMemberUuid(String productCode, String memberUuid);

    @Query("SELECT p.like FROM ProductLike p WHERE p.productCode = :productCode AND p.memberUuid = :memberUuid")
    Optional<Boolean> findIsLikedByProductUuidAndMemberUuid(String productUuid, String memberUuid);
}