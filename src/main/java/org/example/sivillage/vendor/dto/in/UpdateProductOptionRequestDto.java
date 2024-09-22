package org.example.sivillage.vendor.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.vendor.domain.ProductOption;
import org.example.sivillage.vendor.vo.in.UpdateProductOptionRequestVo;

@Getter
@NoArgsConstructor
public class UpdateProductOptionRequestDto {

    private Long id;

    private String productCode;

    private Long sizeId;

    private String volume;

    private Integer stock;

    private Boolean soldOut;

    private Integer dangerStock;

    @Builder
    public UpdateProductOptionRequestDto(Long id, String productCode, Long sizeId, String volume, Integer stock, Boolean soldOut, Integer dangerStock) {
        this.id = id;
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
        this.soldOut = soldOut;
        this.dangerStock = dangerStock;
    }

    public static UpdateProductOptionRequestDto from(UpdateProductOptionRequestVo updateProductOptionRequestVo) {
        return UpdateProductOptionRequestDto.builder()
                .id(updateProductOptionRequestVo.getId())
                .productCode(updateProductOptionRequestVo.getProductCode())
                .sizeId(updateProductOptionRequestVo.getSizeId())
                .volume(updateProductOptionRequestVo.getVolume())
                .stock(updateProductOptionRequestVo.getStock())
                .soldOut(updateProductOptionRequestVo.getSoldOut())
                .dangerStock(updateProductOptionRequestVo.getDangerStock())
                .build();
    }

    public ProductOption updateEntity(Long id) {
        return ProductOption.builder()
                .id(id)
                .productCode(productCode)
                .sizeId(sizeId)
                .volume(volume)
                .stock(stock)
                .soldOut(soldOut)
                .dangerStock(dangerStock)
                .build();
    }
}
