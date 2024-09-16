package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.vendor.domain.ProductOptionList;
import org.example.sivillage.product.vo.out.GetProductOptionListResponseVo;

@NoArgsConstructor
@Getter
public class GetProductOptionListResponseDto {
    private Long productOptionId;
    private Long sizeId;
    private String volume;
    private Integer stock;

    @Builder
    public GetProductOptionListResponseDto(Long productOptionId, Long sizeId, String volume, Integer stock) {

        this.productOptionId = productOptionId;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
    }

    public static GetProductOptionListResponseDto from(ProductOptionList productOptionList) {
        return GetProductOptionListResponseDto.builder()
                .productOptionId(productOptionList.getId())
                .sizeId(productOptionList.getSizeId())
                .volume(productOptionList.getVolume())
                .stock(productOptionList.getStock())
                .build();
    }

    public GetProductOptionListResponseVo toVo() {
        return GetProductOptionListResponseVo.builder()
                .productOptionId(productOptionId)
                .sizeId(sizeId)
                .volume(volume)
                .stock(stock)
                .build();
    }
}
