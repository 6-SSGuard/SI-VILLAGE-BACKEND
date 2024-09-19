package org.example.sivillage.vendor.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.vendor.domain.ProductOptionList;
import org.example.sivillage.vendor.vo.in.CreateProductOptionRequestVo;

@NoArgsConstructor
@Getter
public class CreateProductOptionRequestDto {

    private String productCode;

    private Long sizeId;

    private String volume;

    private Integer stock;

    private Boolean soldOut;

    private Integer dangerStock;

    @Builder
    public CreateProductOptionRequestDto(String productCode, Long sizeId, String volume, Integer stock, Boolean soldOut, Integer dangerStock) {
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
        this.soldOut = soldOut;
        this.dangerStock = dangerStock;
    }

    public static CreateProductOptionRequestDto from(CreateProductOptionRequestVo createProductOptionRequestVo) {
        return CreateProductOptionRequestDto.builder()
                .productCode(createProductOptionRequestVo.getProductCode())
                .sizeId(createProductOptionRequestVo.getSizeId())
                .volume(createProductOptionRequestVo.getVolume())
                .stock(createProductOptionRequestVo.getStock())
                .soldOut(createProductOptionRequestVo.getSoldOut())
                .dangerStock(createProductOptionRequestVo.getDangerStock())
                .build();
    }

    public ProductOptionList toEntity() {
        return ProductOptionList.builder()
                .productCode(productCode)
                .sizeId(sizeId)
                .volume(volume)
                .stock(stock)
                .soldOut(soldOut)
                .dangerStock(dangerStock)
                .build();
    }
}
