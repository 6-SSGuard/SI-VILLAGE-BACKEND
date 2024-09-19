package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.vo.out.CreateProductResponseVo;

@Getter
@NoArgsConstructor
public class CreateProductResponseDto {

    private String productCode;

    @Builder
    public CreateProductResponseDto(String productCode) {
        this.productCode = productCode;
    }

    public static CreateProductResponseDto from(String productCode) {
        return CreateProductResponseDto.builder()
                .productCode(productCode)
                .build();
    }

    public CreateProductResponseVo toVo() {
        return CreateProductResponseVo.builder()
                .productCode(productCode)
                .build();
    }
}
