package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProductResponseVo {

    private String productCode;

    @Builder
    public CreateProductResponseVo(String productCode) {
        this.productCode = productCode;
    }
}
