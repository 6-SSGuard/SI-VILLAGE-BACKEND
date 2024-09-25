package org.example.sivillage.review.infrastructure;

import com.querydsl.core.types.dsl.NumberPath;
import org.example.sivillage.review.domain.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    Optional<ReviewLike> findByReviewIdAndMemberUuid(Long reviewId, String memberUuid);
    @Query("SELECT COUNT(rl) FROM ReviewLike rl WHERE rl.reviewId = :reviewId AND rl.reviewLike = true")
    Long countByReviewIdAndLike(@Param("reviewId") Long reviewId);
}

