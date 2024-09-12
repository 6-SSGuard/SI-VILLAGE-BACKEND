package org.example.sivillage.review.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.review.domain.ReviewLike;

@Getter
@NoArgsConstructor
public class ReviewLikeRequestDto {

    private boolean reviewLike; //


    public void toggleLike() {
        this.reviewLike = !this.reviewLike;
    }

    public static ReviewLike toEntity(ReviewLikeRequestDto reviewLikeRequestDto, String memberUuid, Long reviewId){
        return ReviewLike.builder()
                .reviewLike(reviewLikeRequestDto.isReviewLike())
                .reviewId(reviewId)
                .memberUuid(memberUuid)
                .build();
    }

    @Builder
    public ReviewLikeRequestDto(boolean reviewLike) {
        this.reviewLike = reviewLike;
    }


}
