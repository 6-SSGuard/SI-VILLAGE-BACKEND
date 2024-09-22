package org.example.sivillage.purchase.application;

import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;

public interface PurchaseService {

    /**
     * 단건 주문 추가
     * @param addPurchaseRequestDto 주문 추가 요청 DTO
     */
    void addPurchase(String memberUuid, AddPurchaseRequestDto addPurchaseRequestDto);

    /**
     * 장바구니 주문 추가
     * @param memberUuid 회원 UUID
     * @param addPurchaseFromCartRequestDto 장바구니 주문 추가 요청 DTO
     */
    void addPurchaseFromCart(String memberUuid, AddPurchaseFromCartRequestDto addPurchaseFromCartRequestDto);
}
