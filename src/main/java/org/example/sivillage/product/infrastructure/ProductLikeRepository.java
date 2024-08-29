package org.example.sivillage.product.infrastructure;

import org.example.sivillage.member.domain.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
//    boolean existsByProductAndMember(Product product, Member member);
//    void deleteByProductAndMember(Product product, Member member);
    Integer countByProductUuid(String uuid);

    Optional<ProductLike> findByProductUuidAndMemberUuid(String productUuid, String memberUuid);

    @Query("SELECT p.isLiked FROM ProductLike p WHERE p.productUuid = :productUuid AND p.memberUuid = :memberUuid")
    Optional<Boolean> findIsLikedByProductUuidAndMemberUuid(String productUuid, String memberUuid);
}