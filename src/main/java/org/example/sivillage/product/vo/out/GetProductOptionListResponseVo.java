package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

@Getter
@NoArgsConstructor
public class GetProductOptionListResponseVo {

    private Long productOptionId;
    private Size size;
    private ShoeSize shoeSize;
    private String volume;
    private Integer stock;

    @Builder
    public GetProductOptionListResponseVo(Long productOptionId, Size size, ShoeSize shoeSize, String volume, Integer stock) {
        this.productOptionId = productOptionId;
        this.size = size;
        this.shoeSize = shoeSize;
        this.volume = volume;
        this.stock = stock;
    }
}
