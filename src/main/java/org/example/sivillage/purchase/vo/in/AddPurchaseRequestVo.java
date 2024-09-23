package org.example.sivillage.purchase.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddPurchaseRequestVo {

    private String productCode;
    private Long productOptionId;
    private Integer amount;
    private Long shippingAddressId;
    private String shippingMessage;

    @Builder
    public AddPurchaseRequestVo(String productCode, Long productOptionId, Integer amount, Long shippingAddressId, String shippingMessage) {
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
        this.shippingAddressId = shippingAddressId;
        this.shippingMessage = shippingMessage;
    }
}
