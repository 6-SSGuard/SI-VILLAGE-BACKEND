package org.example.sivillage.product.vo.out;

import lombok.*;

@NoArgsConstructor
@Getter
public class GetProductBriefInfoResponseVo {
    private String productCode;
    private String brandEngName;
    private String productName;
    private Integer price;

    @Builder
    public GetProductBriefInfoResponseVo(String productCode, String brandEngName, String productName, Integer price) {
        this.productCode = productCode;
        this.brandEngName = brandEngName;
        this.productName = productName;
        this.price = price;
    }
}
