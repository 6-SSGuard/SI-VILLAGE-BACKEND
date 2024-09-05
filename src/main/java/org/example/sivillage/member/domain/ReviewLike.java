package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_like_id")
    private Long reviewLikeId;

    @Column(nullable = false)
    private Long reviewId;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean isLiked;


    public static ReviewLike toEntity(Long reviewId, String memberUuid){
        return ReviewLike.builder()
                .reviewId(reviewId)
                .memberUuid(memberUuid)
                .isLiked(false)
                .build();
    }

    public void toggleLike(boolean like){
        this.isLiked = !this.isLiked;
    }
}
