package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.product.dto.in.CreateProductFromCsvRequestDto;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@DynamicUpdate
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String productCode;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Long colorId;

    @Column(nullable = false, length = 10000)
    private String detailContent;

    @Column(nullable = false)
    private Long brandId;

    @Builder
    public Product(Long id, String productCode, String productName, Integer price, Long colorId, String detailContent, Long brandId) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.colorId = colorId;
        this.detailContent = detailContent;
        this.brandId = brandId;
    }

    @Builder
    public Product(String productCode, String productName, Integer price, Long colorId, String detailContent, Long brandId) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.colorId = colorId;
        this.detailContent = detailContent;
        this.brandId = brandId;
    }

    public static Product createProductFromCsv(CreateProductFromCsvRequestDto request) {
        return Product.builder()
                .productName(request.getProductName())
                .productCode(request.getProductCode())
                .price(request.getPrice())
                .detailContent(request.getDetailContent())
                .brandId(request.getBrandId())
                .build();
    }
}
