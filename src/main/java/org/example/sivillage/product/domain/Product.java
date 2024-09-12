package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.product.dto.in.CreateProductFromCsvRequestDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
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
    private Color color;

    @Column(nullable = false, length = 10000)
    private String detailContent;

    private Long brandId;

    public static Product createProductFromCsv(CreateProductFromCsvRequestDto request) {
        return Product.builder()
                .productName(request.getProductName())
                .productCode(request.getProductCode())
                .price(request.getPrice())
                .detailContent(request.getDetailContent())
                .build();
    }

}
