package org.example.sivillage.review.application;
import org.example.sivillage.review.dto.in.ReviewRequestDto;
import org.example.sivillage.review.dto.out.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewRequestDto reviewRequestDto, String memberUuid);
    List<ReviewResponseDto> getReview(String memberUuid);
    void changeReview(ReviewResponseDto reviewResponseDto, String memberUuid);
    void removeReview(Long id);
}
