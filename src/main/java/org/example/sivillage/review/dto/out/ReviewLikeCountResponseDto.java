package org.example.sivillage.review.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.review.vo.out.ReviewLikeCountResponseVo;

@Getter
public class ReviewLikeCountResponseDto {

    private Long reviewLikeCount;

    public static ReviewLikeCountResponseDto from(Long reviewLikeCount){
    return ReviewLikeCountResponseDto.builder()
            .reviewLikeCount(reviewLikeCount)
            .build();

    }

    public ReviewLikeCountResponseVo toResponseVo(){
        return ReviewLikeCountResponseVo.builder()
                .reviewLikeCount(reviewLikeCount)
                .build();
    }

    @Builder
    public ReviewLikeCountResponseDto(Long reviewLikeCount){
        this.reviewLikeCount = reviewLikeCount;
    }
}
