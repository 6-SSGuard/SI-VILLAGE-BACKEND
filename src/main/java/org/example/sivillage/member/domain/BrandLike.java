package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_like_id")
    private Long brandLikeId;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean like;

    public static BrandLike createLikedBrand(Long brandId, String memberUuid) {
        return BrandLike.builder()
                .brandId(brandId)
                .memberUuid(memberUuid)
                .like(false)
                .build();
    }

    public void toggleLike() {
        this.like = !this.like;
    }
}
