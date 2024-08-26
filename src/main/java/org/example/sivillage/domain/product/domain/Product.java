package org.example.sivillage.domain.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.domain.brand.domain.Brand;
import org.example.sivillage.domain.product.vo.CreateProductRequest;
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
    private Long id;

//    @Column(nullable = false, length = 50, unique = true)
//    private String productCode;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer price;

//    @Column(nullable = false, length = 10000)
//    private String detailContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public static Product createProduct(CreateProductRequest request, Brand brand) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .brand(brand)
                .build();
    }
}
