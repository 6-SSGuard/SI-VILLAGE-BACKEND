package org.example.sivillage.review.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.review.dto.ReviewRequestDto;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestVo {

    @Schema(description = "리뷰 내용", example = "매우 만족", required = true)
    @NotNull(message = "리뷰 내용은 필수입니다.")
    @Size(max = 2000, message = "리뷰 내용은 최대 2000자까지 입력할 수 있습니다.")
    private String reviewContent;

    @Schema(description = "리뷰 별점", example = "5.0", required = true)
    @NotNull
    private Double score;

    @Schema(description = "리뷰 이미지 리스트", example = "[\"https://cdn-mo.sivillage.com/mo/assets/comm/image/siv_logo.png\",\"https://cdn-mo.sivillage.com/mo/assets/comm/image/siv_logo.png\"]", required = true)
    private List<String> reviewImageUrl;

    public static ReviewRequestDto toDto (ReviewRequestVo vo) {
        return ReviewRequestDto.builder()
                .reviewContent(vo.getReviewContent())
                .score(vo.getScore())
                .reviewImageUrl(vo.getReviewImageUrl())
                .build();
    }
}
