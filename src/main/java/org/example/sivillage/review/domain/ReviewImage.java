package org.example.sivillage.review.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.review.dto.in.ReviewImageRequestDto;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewImageUrl;

    @Column(nullable = false)
    private Long reviewId;

    @Builder
    public ReviewImage(Long id, String reviewImageUrl, Long reviewId) {
        this.id = id;
        this.reviewImageUrl = reviewImageUrl;
        this.reviewId = reviewId;
    }

}
