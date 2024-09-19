package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class BrandProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_product_id")
    private Long id;

    private Long brandId;

    private String productCode;

    @Builder
    public BrandProduct(Long brandId, String productCode) {
        this.brandId = brandId;
        this.productCode = productCode;
    }

    @Builder
    public BrandProduct(Long id, Long brandId, String productCode) {
        this.id = id;
        this.brandId = brandId;
        this.productCode = productCode;
    }
}
