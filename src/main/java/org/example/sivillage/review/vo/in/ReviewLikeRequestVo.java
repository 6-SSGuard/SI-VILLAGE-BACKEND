package org.example.sivillage.review.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.sivillage.review.dto.in.ReviewLikeRequestDto;

@Getter
public class ReviewLikeRequestVo {

    @Schema(description = "리뷰 좋아요 토글" , example = "true")
    private boolean reviewLike;

    public static ReviewLikeRequestDto toDto(ReviewLikeRequestVo reviewLikeRequestVo){
                return ReviewLikeRequestDto.builder()
                        .reviewLike(reviewLikeRequestVo.isReviewLike())
                        .build();
    }
}
