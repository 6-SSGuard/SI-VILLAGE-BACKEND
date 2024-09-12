package org.example.sivillage.product.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

@NoArgsConstructor
@Getter
public class CreateProductOptionRequestVo {

    private String productCode;

    private Size size;

    private ShoeSize shoeSize;

    private String volume;

    private Integer stock;

    @Builder
    public CreateProductOptionRequestVo(String productCode, Size size, ShoeSize shoeSize, String volume, Integer stock) {
        this.productCode = productCode;
        this.size = size;
        this.shoeSize = shoeSize;
        this.volume = volume;
        this.stock = stock;
    }
}
