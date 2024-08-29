package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_like_id")
    private Long productLikeId;

    @Column(nullable = false)
    private String productUuid;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean isLiked;

    // 좋아요 생성 메서드
    public static ProductLike createProductLike(String productUuid, String memberUuid) {
        return ProductLike.builder()
                .productUuid(productUuid)
                .memberUuid(memberUuid)
                .isLiked(false)
                .build();
    }

    public void toggleLike() {
        this.isLiked = !this.isLiked;
    }
}
