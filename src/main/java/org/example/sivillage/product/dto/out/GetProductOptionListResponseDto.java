package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.ProductOption;
import org.example.sivillage.product.vo.out.GetProductOptionListResponseVo;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

@NoArgsConstructor
@Getter
public class GetProductOptionListResponseDto {
    private Long productOptionId;
    private Size size;
    private ShoeSize shoeSize;
    private String volume;
    private Integer stock;

    @Builder
    public GetProductOptionListResponseDto(Long productOptionId, Size size, ShoeSize shoeSize, String volume, Integer stock) {

        this.productOptionId = productOptionId;
        this.size = size;
        this.shoeSize = shoeSize;
        this.volume = volume;
        this.stock = stock;
    }

    public static GetProductOptionListResponseDto from(ProductOption productOption) {
        return GetProductOptionListResponseDto.builder()
                .productOptionId(productOption.getId())
                .size(productOption.getSize())
                .shoeSize(productOption.getShoeSize())
                .volume(productOption.getVolume())
                .stock(productOption.getStock())
                .build();
    }

    public GetProductOptionListResponseVo toVo() {
        return GetProductOptionListResponseVo.builder()
                .productOptionId(productOptionId)
                .size(size)
                .shoeSize(shoeSize)
                .volume(volume)
                .stock(stock)
                .build();
    }
}
