package org.example.sivillage.review.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.vo.out.ReviewResponseVo;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ReviewResponseDto {

    private Double score;

    private LocalDateTime reviewDate;

    private String reviewContent;

    private String authorEmail;

    private String memberInformation;


    public static ReviewResponseDto from(Review review, String authorEmail, String memberInformation) {
        return ReviewResponseDto.builder()
                .score(review.getScore())
                .reviewDate(review.getCreatedDate())
                .reviewContent(review.getReviewContent())
                .authorEmail(authorEmail)
                .memberInformation(memberInformation)
                .build();
    }

    public ReviewResponseVo toResponseVo() {
        return ReviewResponseVo.builder()
                .score(score)
                .reviewDate(reviewDate)
                .reviewContent(reviewContent)
                .authorEmail(authorEmail)
                .memberInformation(memberInformation)
                .build();
    }

    @Builder
    public ReviewResponseDto(Double score, LocalDateTime reviewDate, String reviewContent, String authorEmail, String memberInformation) {
        this.score = score;
        this.reviewDate = reviewDate;
        this.reviewContent = reviewContent;
        this.authorEmail = authorEmail;
        this.memberInformation = memberInformation;
    }
}
