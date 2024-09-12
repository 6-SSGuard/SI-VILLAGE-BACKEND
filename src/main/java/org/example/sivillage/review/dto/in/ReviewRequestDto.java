package org.example.sivillage.review.dto.in;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.review.domain.Review;

@Getter
public class ReviewRequestDto {

    private Double score;

    private String reviewContent;

    public Review toEntity(ReviewRequestDto reviewRequestDto,String authorEmail, String memberInformation, String memberUuid, String productCode) {
        return Review.builder()
                .score(reviewRequestDto.getScore())
                .reviewContent(reviewRequestDto.getReviewContent())
                .authorEmail(authorEmail.substring(0, 4) + "*******")
                .memberInformation(memberInformation)
                .memberUuid(memberUuid)
                .productCode(productCode)
                .build();
    }

    public Review updateToEntity(ReviewRequestDto reviewRequestDto, Review review) {
        return Review.builder()
                .id(review.getId())
                .score(reviewRequestDto.getScore())
                .reviewContent(review.getReviewContent())
                .memberUuid(review.getMemberUuid())
                .productCode(review.getProductCode())
                .build();
    }

    @Builder
    public ReviewRequestDto(Double score, String reviewContent) {
        this.score = score;
        this.reviewContent = reviewContent;
    }
}
