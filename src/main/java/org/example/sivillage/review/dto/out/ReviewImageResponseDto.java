package org.example.sivillage.review.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.vo.out.ReviewImageResponseVo;

@Getter
@NoArgsConstructor
public class ReviewImageResponseDto {

    private Long reviewImageId;
    private String reviewImageUrl;

    public static ReviewImageResponseDto from(ReviewImage reviewImage){
        return ReviewImageResponseDto.builder()
                .reviewImageId(reviewImage.getId())
                .reviewImageUrl(reviewImage.getReviewImageUrl())
                .build();
    }

    public ReviewImageResponseVo toResponseVo(){
      return ReviewImageResponseVo.builder()
              .reviewImageId(reviewImageId)
              .reviewImageUrl(reviewImageUrl)
              .build();
    }

    @Builder
    public ReviewImageResponseDto(Long reviewImageId, String reviewImageUrl) {
        this.reviewImageId = reviewImageId;
        this.reviewImageUrl = reviewImageUrl;
    }

}
