package org.example.sivillage.review.infrastructure;

import org.example.sivillage.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductUuid(String productUuid);
    List<Review> findByMemberUuid(String memberUuid);
    Optional<Review> findByReviewIdAndMemberUuid(Long reviewId, String memberUuid);
}
