package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false, length = 50, unique = true)
    private String productUuid;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 10000)
    private String detailContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public static Product createProduct(CreateProductRequestVo request, Brand brand, String productUuid) {
        return Product.builder()
                .productName(request.getProductName())
                .productUuid(productUuid)
                .price(request.getPrice())
                .detailContent(request.getDetailContent())
                .brand(brand)
                .build();
    }
}
