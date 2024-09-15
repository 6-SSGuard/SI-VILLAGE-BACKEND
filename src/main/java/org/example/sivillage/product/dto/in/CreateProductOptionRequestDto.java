package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.ProductOption;
import org.example.sivillage.product.vo.in.CreateProductOptionRequestVo;

@NoArgsConstructor
@Getter
public class CreateProductOptionRequestDto {

    private String productCode;

    private Long sizeId;

    private String volume;

    private Integer stock;

    @Builder
    public CreateProductOptionRequestDto(String productCode, Long sizeId, String volume, Integer stock) {
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.volume = volume;
        this.stock = stock;
    }

    public static CreateProductOptionRequestDto from(CreateProductOptionRequestVo createProductOptionRequestVo) {
        return CreateProductOptionRequestDto.builder()
                .productCode(createProductOptionRequestVo.getProductCode())
                .sizeId(createProductOptionRequestVo.getSizeId())
                .volume(createProductOptionRequestVo.getVolume())
                .stock(createProductOptionRequestVo.getStock())
                .build();
    }

    public ProductOption toEntity() {
        return ProductOption.builder()
                .productCode(productCode)
                .sizeId(sizeId)
                .volume(volume)
                .stock(stock)
                .build();
    }
}
