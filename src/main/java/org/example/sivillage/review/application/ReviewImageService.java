package org.example.sivillage.review.application;

import org.example.sivillage.review.dto.in.ReviewImageRequestDto;
import org.example.sivillage.review.dto.out.ReviewImageResponseDto;

import java.util.List;

public interface ReviewImageService {
    void addReviewImage(ReviewImageRequestDto reviewImageRequestDto, Long reviewId);
    List<ReviewImageResponseDto> getReviewImage(Long reviewId);
    void changeReviewImage(ReviewImageResponseDto reviewImageResponseDto, Long reviewId);
    void removeReviewImage(Long id);
}
