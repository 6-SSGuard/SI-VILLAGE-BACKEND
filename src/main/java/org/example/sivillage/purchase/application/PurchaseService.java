package org.example.sivillage.purchase.application;

import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;

public interface PurchaseService {

    /**
     * 단건 주문 추가
     * @param addPurchaseRequestDto
     */
    void addPurchase(AddPurchaseRequestDto addPurchaseRequestDto);

    void addPurchaseFromCart(AddPurchaseFromCartRequestDto addPurchaseFromCartRequestDto);
}
