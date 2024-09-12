package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.review.domain.ReviewLike;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.infrastructure.ReviewLikeRepository;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Transactional
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;

    public void toggleReviewLike(Long reviewId, String memberUuid){

        Review review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        ReviewLike reviewLike = reviewLikeRepository.findByReviewIdAndMemberUuid(reviewId, memberUuid)
                .orElse(ReviewLike.toEntity(reviewId,memberUuid));

        if(reviewLike.isLiked()){ // 좋아요 된 상태
            reviewLike.toggleLike(false); // 좋아요 해제
            review.decrementLikeCount();

        } else {
            reviewLike.toggleLike(true);// 좋아요
            review.incrementLikeCount();
            reviewRepository.save(review);
            reviewLikeRepository.save(reviewLike);}
    }


}
