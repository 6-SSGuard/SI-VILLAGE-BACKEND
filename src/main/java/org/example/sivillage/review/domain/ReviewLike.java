package org.example.sivillage.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reviewId;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean reviewLike;

    @Builder
    public ReviewLike(Long reviewId, String memberUuid, boolean reviewLike) {
        this.reviewId = reviewId;
        this.memberUuid = memberUuid;
        this.reviewLike = reviewLike;
    }

    public void toggleLike() {
        this.reviewLike = !this.reviewLike;
    }

    public static ReviewLike toEntity(String memberUuid, Long reviewId){
        return ReviewLike.builder()
                .reviewLike(true)
                .reviewId(reviewId)
                .memberUuid(memberUuid)
                .build();
    }

}