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
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long id;

    @Column(nullable = false)
    private String productCode;

    private Long sizeId;

    private String volume;

    @Column(nullable = false)
    private Integer stock;

    private Boolean soldOut;

    private Integer dangerStock;

    @Builder
    public ProductOption(
            String productCode,
            Long sizeId,
            String volume,
            Integer stock,
            Boolean soldOut,
            Integer dangerStock
    ) {
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
        this.soldOut = soldOut;
        this.dangerStock = dangerStock;
    }

    public boolean isSizeOption() {
        return sizeId != null;
    }

}
