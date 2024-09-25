package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductViewCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double viewCount;

    @Column(nullable = false)
    private String productCode;

    @Builder
    public ProductViewCount(Long id, Double viewCount, String productCode) {
        this.id = id;
        this.viewCount = viewCount;
        this.productCode = productCode;
    }
}

