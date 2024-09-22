package org.example.sivillage.purchase.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.purchase.vo.out.GetPurchaseDetailResponseVo;

@Getter
@NoArgsConstructor
public class GetPurchaseDetailResponseDto {

    private String productName;
    private int quantity;
    private int chargedPrice;
    private String volume;
    private String color;
    private String size;

    @Builder
    public GetPurchaseDetailResponseDto(String productName, int quantity, int chargedPrice, String volume, String color, String size) {
        this.productName = productName;
        this.quantity = quantity;
        this.chargedPrice = chargedPrice;
        this.volume = volume;
        this.color = color;
        this.size = size;
    }

    public GetPurchaseDetailResponseVo toVo() {
        return GetPurchaseDetailResponseVo.builder()
                .productName(productName)
                .quantity(quantity)
                .chargedPrice(chargedPrice)
                .volume(volume)
                .color(color)
                .size(size)
                .build();
    }
}
