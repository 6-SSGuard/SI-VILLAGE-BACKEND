package org.example.sivillage.purchase.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.purchase.domain.PayState;
import org.example.sivillage.purchase.domain.Purchase;
import org.example.sivillage.purchase.domain.PurchaseProduct;
import org.example.sivillage.purchase.vo.in.AddPurchaseRequestVo;

@Getter
@NoArgsConstructor
public class AddPurchaseRequestDto {

    private String productCode;
    private Long productOptionId;
    private Integer amount;
    private Long shippingAddressId;
    private String shippingMessage;

    @Builder
    public AddPurchaseRequestDto(String productCode, Long productOptionId, Integer amount, Long shippingAddressId, String shippingMessage) {
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
        this.shippingAddressId = shippingAddressId;
        this.shippingMessage = shippingMessage;
    }

    public static AddPurchaseRequestDto from(AddPurchaseRequestVo vo) {
        return AddPurchaseRequestDto.builder()
            .productCode(vo.getProductCode())
            .productOptionId(vo.getProductOptionId())
            .amount(vo.getAmount())
            .shippingAddressId(vo.getShippingAddressId())
            .shippingMessage(vo.getShippingMessage())
            .build();
    }

    public Purchase toEntity(String purchaseCode, String memberUuid, Integer totalPriceBeforeDiscount, Integer totalPriceAfterDiscount) {
        return Purchase.builder()
            .purchaseCode(purchaseCode)
            .memberUuid(memberUuid)
            .shippingMessage(shippingMessage)
            .shippingAddressId(shippingAddressId)
            .totalPriceBeforeDiscount(totalPriceBeforeDiscount)
            .totalPriceAfterDiscount(totalPriceAfterDiscount)
            .payState(PayState.PAYMENT_READY)
            .build();
    }

    public PurchaseProduct toEntity(String purchaseCode) {
        return PurchaseProduct.builder()
            .purchaseCode(purchaseCode)
            .productCode(productCode)
            .productOptionId(productOptionId)
            .amount(amount)
            .build();
    }
}
