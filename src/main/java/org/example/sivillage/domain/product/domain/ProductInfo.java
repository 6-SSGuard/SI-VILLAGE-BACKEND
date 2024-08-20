package org.example.sivillage.domain.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.domain.product.vo.CreateProductRequest;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;

    @Column(nullable = false)
    private Size size;

    @Column(nullable = false)
    private String capacity;

    @Column(nullable = false)
    private Integer stock;

    @Builder
    public static ProductInfo createProductInfo(CreateProductRequest request, Product product) {
        return ProductInfo.builder()
                .color(request.getColor())
                .size(request.getSize())
                .capacity(request.getCapacity())
                .stock(request.getStock())
                .product(product)
                .build();
    }
}
