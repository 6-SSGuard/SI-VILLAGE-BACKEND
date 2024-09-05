package org.example.sivillage.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

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

    private String productCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private ShoeSize shoeSize;

    @Column(nullable = true)
    private String volume;

    @Column(nullable = false)
    private Integer stock;

    public static ProductOption createProductOption(CreateProductRequestDto request, String productCode) {
        return ProductOption.builder()
                .color(request.getColor())
                .size(request.getSize())
                .shoeSize(request.getShoeSize())
                .volume(request.getVolume())
                .stock(request.getStock())
                .productCode(productCode)
                .build();
    }
}
