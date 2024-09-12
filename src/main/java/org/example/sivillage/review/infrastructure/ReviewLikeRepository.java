package org.example.sivillage.review.infrastructure;

import org.example.sivillage.review.domain.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    Optional<ReviewLike> findByReviewIdAndMemberUuid(Long reviewId, String memberUuid);
}
