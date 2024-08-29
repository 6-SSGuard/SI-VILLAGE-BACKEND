package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;
import org.example.sivillage.productoption.domain.Size;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_info_id")
    private Long productInfoId;

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
    public static ProductOption createProductOption(CreateProductRequestVo request, Product product) {
        return ProductOption.builder()
                .color(request.getColor())
                .size(request.getSize())
                .capacity(request.getCapacity())
                .stock(request.getStock())
                .product(product)
                .build();
    }
}
