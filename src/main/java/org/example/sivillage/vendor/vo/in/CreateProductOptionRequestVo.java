package org.example.sivillage.vendor.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateProductOptionRequestVo {

    private String productCode;

    private Long sizeId;

    private String volume;

    private Integer stock;

    private Boolean soldOut;

    private Integer dangerStock;

    @Builder
    public CreateProductOptionRequestVo(String productCode, Long sizeId, String volume, Integer stock, Boolean soldOut, Integer dangerStock) {
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
        this.soldOut = soldOut;
        this.dangerStock = dangerStock;
    }
}
