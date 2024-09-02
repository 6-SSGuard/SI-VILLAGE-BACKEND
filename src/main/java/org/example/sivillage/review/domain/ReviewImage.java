package org.example.sivillage.review.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Builder
    public ReviewImage(Long id, String reviewImageUrl, Review review) {
        this.id = id;
        this.reviewImageUrl = reviewImageUrl;
        this.review = review;
    }

    public static ReviewImage toEntity(String reviewImageUrl, Review review) {
        return ReviewImage.builder()
                .reviewImageUrl(reviewImageUrl)
                .review(review)
                .build();
    }
}
