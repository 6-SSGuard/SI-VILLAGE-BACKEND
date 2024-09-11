package org.example.sivillage.review.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.sivillage.review.dto.in.ReviewImageRequestDto;

import java.util.List;

@Getter
public class ReviewImageRequestVo {

    @Schema(description = "리뷰 이미지 리스트", example = "[\"https://cdn-mo.sivillage.com/mo/assets/comm/image/siv_logo.png\",\"https://cdn-mo.sivillage.com/mo/assets/comm/image/siv_logo.png\"]", required = true)
    private List<String> reviewImageUrl;

    public static ReviewImageRequestDto toDto(ReviewImageRequestVo reviewImageRequestVo) {
        return ReviewImageRequestDto.builder()
                .reviewImageUrl(reviewImageRequestVo.getReviewImageUrl())
                .build();
    }
}
