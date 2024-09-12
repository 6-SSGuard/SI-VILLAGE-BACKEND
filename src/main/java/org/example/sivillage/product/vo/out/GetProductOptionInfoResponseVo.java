package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.Color;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

@NoArgsConstructor
@Getter
public class GetProductOptionInfoResponseVo {

    private Long productOptionId;
    private Color color;
    private Size size;
    private ShoeSize shoeSize;
    private String volume;
    private Integer stock;

    @Builder
    public GetProductOptionInfoResponseVo(Long productOptionId, Color color, Size size, ShoeSize shoeSize, String volume, Integer stock) {
        this.productOptionId = productOptionId;
        this.color = color;
        this.size = size;
        this.shoeSize = shoeSize;
        this.volume = volume;
        this.stock = stock;
    }
}
