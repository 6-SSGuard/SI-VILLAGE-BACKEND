package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductDetailsResponseVo {
    private String productCode;
    private String productName;
    private Integer price;
    private String brandEngName;
    private Long colorId;
    private String detailContent;

    @Builder
    public GetProductDetailsResponseVo(String productCode, String productName, Integer price, String brandEngName, Long colorId, String detailContent) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.brandEngName = brandEngName;
        this.colorId = colorId;
        this.detailContent = detailContent;
    }
}
