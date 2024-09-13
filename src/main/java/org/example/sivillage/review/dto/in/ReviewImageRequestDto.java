package org.example.sivillage.review.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.review.domain.ReviewImage;

import java.util.List;

@Getter
public class ReviewImageRequestDto {

    private List<String> reviewImageUrl;

    public static ReviewImage toEntity(String reviewImageUrl, Long reviewId) {
        return ReviewImage.builder()
                .reviewId(reviewId)
                .reviewImageUrl(reviewImageUrl)
                .build();
    }

    @Builder
    public ReviewImageRequestDto(List<String> reviewImageUrl) {
        this.reviewImageUrl = reviewImageUrl;
    }

}
