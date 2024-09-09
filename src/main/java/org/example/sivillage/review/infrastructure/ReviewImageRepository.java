package org.example.sivillage.review.infrastructure;

import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List <ReviewImage> findByReview(Review review);
    void deleteAllByReview(Review review);
}
