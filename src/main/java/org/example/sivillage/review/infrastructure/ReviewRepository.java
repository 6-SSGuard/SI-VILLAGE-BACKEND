package org.example.sivillage.review.infrastructure;

import io.lettuce.core.dynamic.annotation.Param;
import org.example.sivillage.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r.id FROM Review r WHERE r.productCode = :productCode")
    List<Long> findIdByProductCode(@Param("productCode") String productCode);

    @Query("SELECT r.id FROM Review r WHERE r.memberUuid = :memberUuid")
    List<Long> findIdByMemberUuid(@Param("memberUuid") String memberUuid);
}
