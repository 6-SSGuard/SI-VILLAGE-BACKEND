package org.example.sivillage.review.application;

import org.example.sivillage.review.dto.out.ReviewLikeCountResponseDto;

public interface ReviewLikeService{
    public void toggleReviewLike(Long reviewId, String memberUuid);
    ReviewLikeCountResponseDto getReviewLikeCount(Long reviewId);
}
