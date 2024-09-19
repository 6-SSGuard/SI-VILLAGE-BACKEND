package org.example.sivillage.vendor.domain;

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

//    public static ProductImage createProductImage(CreateProductRequestDto.ProductImageDto imageDto, String productCode) {
//        return ProductImage.builder()
//                .productImageUrl(imageDto.getProductImageUrl())
//                .productCode(productCode)
//                .thumbnail(imageDto.isThumbnail())
//                .build();
//    }
}
