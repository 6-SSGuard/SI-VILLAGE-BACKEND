package org.example.sivillage.product.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateProductRequestVo {

    private String productName;
    private Integer price;
    private Long brandId;
    private String detailContent;
    private Long colorId;

    @Builder
    public CreateProductRequestVo(String productName, Integer price, Long brandId, String detailContent, Long colorId) {
        this.productName = productName;
        this.price = price;
        this.brandId = brandId;
        this.detailContent = detailContent;
        this.colorId = colorId;
    }
}
