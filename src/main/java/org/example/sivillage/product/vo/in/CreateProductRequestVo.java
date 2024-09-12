package org.example.sivillage.product.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.Color;

@NoArgsConstructor
@Getter
public class CreateProductRequestVo {

    private String productName;
    private Integer price;
    private Long brandId;
    private String detailContent;
    private Color color;

    @Builder
    public CreateProductRequestVo(String productName, Integer price, Long brandId, String detailContent, Color color) {
        this.productName = productName;
        this.price = price;
        this.brandId = brandId;
        this.detailContent = detailContent;
        this.color = color;
    }
}
