package org.example.sivillage.product.vo.in;

import lombok.*;

@Getter
public class CreateProductRequestVo {

    private String productName;

    private Integer price;

    private Long brandId;

    private String detailContent;

    @Builder
    public CreateProductRequestVo(String productName, Integer price, Long brandId, String detailContent) {
        this.productName = productName;
        this.price = price;
        this.brandId = brandId;
        this.detailContent = detailContent;
    }
}
