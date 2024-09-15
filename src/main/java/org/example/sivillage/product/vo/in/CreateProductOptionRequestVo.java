package org.example.sivillage.product.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

@NoArgsConstructor
@Getter
public class CreateProductOptionRequestVo {

    private String productCode;

    private Long sizeId;

    private ShoeSize shoeSize;

    private String volume;

    private Integer stock;

    @Builder
    public CreateProductOptionRequestVo(String productCode, Long sizeId, ShoeSize shoeSize, String volume, Integer stock) {
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.shoeSize = shoeSize;
        this.volume = volume;
        this.stock = stock;
    }
}
