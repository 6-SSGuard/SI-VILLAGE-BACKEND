package org.example.sivillage.purchase.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AddPurchaseFromCartRequestVo {

    private List<Long> cartIdList;
    private Long shippingAddressId;
    private String shippingMessage;

    public AddPurchaseFromCartRequestVo(List<Long> cartIdList, Long shippingAddressId, String shippingMessage) {
        this.cartIdList = cartIdList;
        this.shippingAddressId = shippingAddressId;
        this.shippingMessage = shippingMessage;
    }
}
