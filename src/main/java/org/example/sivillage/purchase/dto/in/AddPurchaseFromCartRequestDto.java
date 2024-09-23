package org.example.sivillage.purchase.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.purchase.domain.PayStatus;
import org.example.sivillage.purchase.domain.Purchase;
import org.example.sivillage.purchase.domain.PurchaseProduct;
import org.example.sivillage.purchase.vo.in.AddPurchaseFromCartRequestVo;

import java.util.List;

@Getter
@NoArgsConstructor
public class AddPurchaseFromCartRequestDto {

    private List<Long> cartIdList;
    private Long shippingAddressId;
    private String shippingMessage;

    @Builder
    public AddPurchaseFromCartRequestDto(List<Long> cartIdList, Long shippingAddressId, String shippingMessage) {
        this.cartIdList = cartIdList;
        this.shippingAddressId = shippingAddressId;
        this.shippingMessage = shippingMessage;
    }

    public static AddPurchaseFromCartRequestDto from(AddPurchaseFromCartRequestVo vo) {
        return AddPurchaseFromCartRequestDto.builder()
            .cartIdList(vo.getCartIdList())
            .shippingAddressId(vo.getShippingAddressId())
            .shippingMessage(vo.getShippingMessage())
            .build();
    }

    public static PurchaseProduct toEntity(String purchaseCode, Cart cart, Integer chargedPrice) {
        return PurchaseProduct.builder()
                .purchaseCode(purchaseCode)
                .productCode(cart.getProductCode())
                .productOptionId(cart.getProductOptionId())
                .amount(cart.getQuantity())
                .chargedPrice(chargedPrice)
                .build();
    }

    public Purchase toEntity(String purchaseCode, String memberUuid, int totalPriceBeforeDiscount, int totalPriceAfterDiscount) {
        return Purchase.builder()
            .purchaseCode(purchaseCode)
            .memberUuid(memberUuid)
            .shippingMessage(shippingMessage)
            .shippingAddressId(shippingAddressId)
            .totalPriceBeforeDiscount(totalPriceBeforeDiscount)
            .totalPriceAfterDiscount(totalPriceAfterDiscount)
            .payStatus(PayStatus.PAYMENT_READY)
            .build();
    }


}
