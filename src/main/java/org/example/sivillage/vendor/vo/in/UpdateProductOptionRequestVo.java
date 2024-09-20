package org.example.sivillage.vendor.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProductOptionRequestVo {

    private Long id;

    private String productCode;

    private Long sizeId;

    private String volume;

    private Integer stock;

    private Boolean soldOut;

    private Integer dangerStock;

    @Builder
    public UpdateProductOptionRequestVo(Long id, String productCode, Long sizeId, String volume, Integer stock, Boolean soldOut, Integer dangerStock) {
        this.id = id;
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
        this.soldOut = soldOut;
        this.dangerStock = dangerStock;
    }
}
