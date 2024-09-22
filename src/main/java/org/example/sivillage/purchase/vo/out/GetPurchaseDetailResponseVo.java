package org.example.sivillage.purchase.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPurchaseDetailResponseVo {

    private String productName;
    private int quantity;
    private int chargedPrice;
    private String volume;
    private String color;
    private String size;

    @Builder
    public GetPurchaseDetailResponseVo(String productName, int quantity, int chargedPrice, String volume, String color, String size) {
        this.productName = productName;
        this.quantity = quantity;
        this.chargedPrice = chargedPrice;
        this.volume = volume;
        this.color = color;
        this.size = size;
    }
}
