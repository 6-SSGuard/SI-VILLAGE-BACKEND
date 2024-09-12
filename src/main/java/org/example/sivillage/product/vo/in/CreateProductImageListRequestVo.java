package org.example.sivillage.product.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProductImageListRequestVo {

    private String productCode;

    private String imageUrl;

    private boolean thumbnail;

    @Builder
    public CreateProductImageListRequestVo(String productCode, String imageUrl, boolean thumbnail) {
        this.productCode = productCode;
        this.imageUrl = imageUrl;
        this.thumbnail = thumbnail;
    }
}
