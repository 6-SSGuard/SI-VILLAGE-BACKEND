package org.example.sivillage.product.domain;

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
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean liked;

    // 좋아요 생성 메서드
    public static ProductLike toEntity(String productCode, String memberUuid) {
        return ProductLike.builder()
                .productCode(productCode)
                .memberUuid(memberUuid)
                .liked(false)
                .build();
    }

    public void toggleLike() {
        this.liked = !this.liked;
    }
}
