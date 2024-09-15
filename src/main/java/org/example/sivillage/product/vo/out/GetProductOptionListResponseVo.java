package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductOptionListResponseVo {

    private Long productOptionId;
    private Long sizeId;
    private String volume;
    private Integer stock;

    @Builder
    public GetProductOptionListResponseVo(Long productOptionId, Long sizeId, String volume, Integer stock) {
        this.productOptionId = productOptionId;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
    }
}
