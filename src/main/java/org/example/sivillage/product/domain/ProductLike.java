package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ProductLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private boolean liked;

    public void toggleLike() {
        this.liked = !this.liked;
    }

    @Builder
    public ProductLike(Long id, String productCode, String memberUuid, boolean liked) {
        this.id = id;
        this.productCode = productCode;
        this.memberUuid = memberUuid;
        this.liked = liked;
    }

    public static ProductLike toEntity(String productCode, String memberUuid) {
        return ProductLike.builder()
                .productCode(productCode)
                .memberUuid(memberUuid)
                .liked(false)
                .build();
    }
}
