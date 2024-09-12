package org.example.sivillage.review.application;
import org.example.sivillage.review.dto.in.ReviewRequestDto;
import org.example.sivillage.review.dto.out.ReviewResponseDto;

public interface ReviewService {
    Long addReview(ReviewRequestDto reviewRequestDto, String memberUuid, String productUuid);
    List<ReviewResponseDto> getReview(Long reviewId);
    void changeReview(ReviewRequestDto reviewRequestDto, Long reviewId);
    void removeReview(Long reviewId);
}
