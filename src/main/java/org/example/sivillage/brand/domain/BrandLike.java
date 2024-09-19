package org.example.sivillage.brand.domain;

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
    private Long id;

    @Column(nullable = false)
    private Long brandId;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean liked;

    public static BrandLike toEntity(Long brandId, String memberUuid) {
        return BrandLike.builder()
                .brandId(brandId)
                .memberUuid(memberUuid)
                .liked(false)
                .build();
    }

    public void toggleLike() {
        this.liked = !this.liked;
    }
}
