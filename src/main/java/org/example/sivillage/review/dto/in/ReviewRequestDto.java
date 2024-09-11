package org.example.sivillage.review.dto.in;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.review.domain.Review;

@Getter
public class ReviewRequestDto {

    private Double score;

    private String reviewContent;

    public Review toEntity(ReviewRequestDto reviewRequestDto, String memberUuid, String productUuid) {
        return Review.builder()
                .score(reviewRequestDto.getScore())
                .reviewContent(reviewRequestDto.getReviewContent())
                .memberUuid(memberUuid)
                .productUuid(productUuid)
                .build();
    }

    public Review updateToEntity(ReviewRequestDto reviewRequestDto, Review review) {
        return Review.builder()
                .id(review.getId())
                .score(reviewRequestDto.getScore())
                .reviewContent(review.getReviewContent())
                .memberUuid(review.getMemberUuid())
                .productUuid(review.getProductUuid())
                .build();
    }

    @Builder
    public ReviewRequestDto(Double score, String reviewContent) {
        this.score = score;
        this.reviewContent = reviewContent;
    }
}
