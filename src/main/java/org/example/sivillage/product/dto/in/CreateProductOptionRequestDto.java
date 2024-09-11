package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.Color;
import org.example.sivillage.product.domain.ProductOption;
import org.example.sivillage.product.vo.in.CreateProductOptionRequestVo;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

@NoArgsConstructor
@Getter
public class CreateProductOptionRequestDto {

    private String productCode;

    private Color color;

    private Size size;

    private ShoeSize shoeSize;

    private String volume;

    private Integer stock;

    @Builder
    public CreateProductOptionRequestDto(String productCode, Color color, Size size, ShoeSize shoeSize, String volume, Integer stock) {
        this.productCode = productCode;
        this.color = color;
        this.size = size;
        this.shoeSize = shoeSize;
        this.volume = volume;
        this.stock = stock;
    }

    public static CreateProductOptionRequestDto from(CreateProductOptionRequestVo createProductOptionRequestVo) {
        return CreateProductOptionRequestDto.builder()
                .productCode(createProductOptionRequestVo.getProductCode())
                .color(createProductOptionRequestVo.getColor())
                .size(createProductOptionRequestVo.getSize())
                .shoeSize(createProductOptionRequestVo.getShoeSize())
                .volume(createProductOptionRequestVo.getVolume())
                .stock(createProductOptionRequestVo.getStock())
                .build();
    }

    public ProductOption toEntity() {
        return ProductOption.builder()
                .productCode(productCode)
                .color(color)
                .size(size)
                .shoeSize(shoeSize)
                .volume(volume)
                .stock(stock)
                .build();
    }
}
