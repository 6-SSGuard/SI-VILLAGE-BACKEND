package org.example.sivillage.product.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeProductRequestVo {

    private String productCode;
    private String productName;
    private Integer price;
    private Long colorId;
    private String detailContent;
    private Long brandId;

    @Builder
    public ChangeProductRequestVo(String productCode, String productName, Integer price, Long colorId, String detailContent, Long brandId) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.colorId = colorId;
        this.detailContent = detailContent;
        this.brandId = brandId;
    }
}
