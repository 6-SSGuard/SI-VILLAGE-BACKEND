package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.Color;

@Getter
@NoArgsConstructor
public class GetProductDetailsResponseVo {
    private String productCode;
    private String productName;
    private Integer price;
    private String brandEngName;
    private Color color;
    private String detailContent;

    @Builder
    public GetProductDetailsResponseVo(String productCode, String productName, Integer price, String brandEngName, Color color, String detailContent) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.brandEngName = brandEngName;
        this.color = color;
        this.detailContent = detailContent;
    }
}
