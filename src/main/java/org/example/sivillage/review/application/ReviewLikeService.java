package org.example.sivillage.review.application;

import org.example.sivillage.review.dto.in.ReviewLikeRequestDto;
import org.example.sivillage.review.dto.out.ReviewLikeCountResponseDto;

public interface ReviewLikeService{
    void toggleReviewLike(ReviewLikeRequestDto reviewLikeRequestDto, Long reviewId, String memberUuid);
    ReviewLikeCountResponseDto getReviewLikeCount(Long reviewId);
}
