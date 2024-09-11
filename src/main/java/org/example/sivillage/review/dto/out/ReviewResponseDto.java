package org.example.sivillage.review.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.vo.out.ReviewResponseVo;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private Double score;

    private LocalDateTime reviewDate;

    private String reviewContent;

    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .score(review.getScore())
                .reviewDate(review.getCreatedDate())
                .reviewContent(review.getReviewContent())
                .build();
    }

    public ReviewResponseVo toResponseVo() {
        return ReviewResponseVo.builder()
                .score(score)
                .reviewDate(reviewDate)
                .reviewContent(reviewContent)
                .build();
    }

    @Builder
    public ReviewResponseDto(Double score, LocalDateTime reviewDate, String reviewContent) {
        this.score = score;
        this.reviewDate = reviewDate;
        this.reviewContent = reviewContent;
    }
}
