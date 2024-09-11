package org.example.sivillage.product.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.Color;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

@NoArgsConstructor
@Getter
public class CreateProductOptionRequestVo {

    private String productCode;

    private Color color;

    private Size size;

    private ShoeSize shoeSize;

    private String volume;

    private Integer stock;

    @Builder
    public CreateProductOptionRequestVo(String productCode, Color color, Size size, ShoeSize shoeSize, String volume, Integer stock) {
        this.productCode = productCode;
        this.color = color;
        this.size = size;
        this.shoeSize = shoeSize;
        this.volume = volume;
        this.stock = stock;
    }
}
