package org.example.sivillage.vendor.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long id;

    @Column(nullable = false, length = 200)
    private String productImageUrl;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private boolean thumbnail;

    @Builder
    public ProductImage(Long id, String productImageUrl, String productCode, boolean thumbnail) {
        this.id = id;
        this.productImageUrl = productImageUrl;
        this.productCode = productCode;
        this.thumbnail = thumbnail;
    }

    @Builder
    public ProductImage(String productImageUrl, String productCode, boolean thumbnail) {
        this.productImageUrl = productImageUrl;
        this.productCode = productCode;
        this.thumbnail = thumbnail;
    }
}
