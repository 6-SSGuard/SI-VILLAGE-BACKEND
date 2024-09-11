package org.example.sivillage.review.dto.out;

import lombok.Builder;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.vo.out.ReviewImageResponseVo;

public class ReviewImageResponseDto {

    private String reviewImageUrl;

    public static ReviewImageResponseDto from(ReviewImage reviewImage){
        return ReviewImageResponseDto.builder()
                .reviewImageUrl(reviewImage.getReviewImageUrl())
                .build();
    }

    public ReviewImageResponseVo toResponseVo(){
      return ReviewImageResponseVo.builder()
              .reviewImageUrl(reviewImageUrl)
              .build();
    }

    @Builder
    public ReviewImageResponseDto(String reviewImageUrl) {
        this.reviewImageUrl = reviewImageUrl;
    }

}
