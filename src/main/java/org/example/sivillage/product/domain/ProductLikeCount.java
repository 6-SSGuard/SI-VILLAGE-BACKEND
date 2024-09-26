package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductLikeCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private String productCode;

    @Builder
    public ProductLikeCount(Long likeCount, String productCode) {
        this.likeCount = likeCount;
        this.productCode = productCode;
    }
}
