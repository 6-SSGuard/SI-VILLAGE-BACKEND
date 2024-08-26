package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.product.vo.CreateProductRequest;
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
    private String productCode;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 10000)
    private String detailContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public static Product createProduct(CreateProductRequest request, Brand brand, String productCode) {
        return Product.builder()
                .productName(request.getProductName())
                .productCode(productCode)
                .price(request.getPrice())
                .brand(brand)
                .build();
    }
}
