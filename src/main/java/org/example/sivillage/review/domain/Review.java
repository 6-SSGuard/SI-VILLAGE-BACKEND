package org.example.sivillage.review.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.review.dto.ReviewRequestDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private String authorEmail;

    @Column(nullable = false, length = 2000)
    private String reviewContent;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String productUuid;

    @Column(nullable = false)
    private Integer reviewLikeCount;


    @Builder
    public Review(Long reviewId, Double score, String authorEmail, String reviewContent, Integer reviewLikeCount, String memberUuid,String productUuid) {
        this.reviewId = reviewId;
        this.score = score;
        this.authorEmail = authorEmail;
        this.reviewContent = reviewContent;
        this.reviewLikeCount = reviewLikeCount;
        this.memberUuid = memberUuid;
        this.productUuid = productUuid;
    }

    public static Review toEntity (ReviewRequestDto dto, String authorEmail, String memberUuid, String productUuid) {
        return Review.builder()
                .score(dto.getScore())
                .authorEmail(authorEmail)
                .reviewContent(dto.getReviewContent())
                .reviewLikeCount(0)
                .memberUuid(memberUuid)
                .productUuid(productUuid)
                .build();
    }

    public void change(ReviewRequestDto dto){
        this.score = dto.getScore();
        this.reviewContent = dto.getReviewContent();
    }
}
