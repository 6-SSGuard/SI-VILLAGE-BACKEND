package org.example.sivillage.brand.infrastructure;

import org.example.sivillage.member.domain.BrandLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BrandLikeRepository extends JpaRepository<BrandLike, Long> {
    Optional<BrandLike> findByBrandIdAndMemberUuid(Long brandId, String memberUuid);

    @Query("SELECT b.isLiked FROM BrandLike b WHERE b.brandId = :brandId AND b.memberUuid = :memberUuid")
    Optional<Boolean> findIsLikedByBrandIdAndMemberUuid(@Param("brandId") Long brandId, @Param("memberUuid") String memberUuid);
}
