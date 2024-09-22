package org.example.sivillage.purchase.application;

import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;
import org.example.sivillage.purchase.dto.out.GetPurchaseCodeListResponseDto;
import org.example.sivillage.purchase.dto.out.GetPurchaseDetailResponseDto;

import java.util.List;

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

    /**
     * 주문 코드 목록 조회
     * @param memberUuid 회원 UUID
     * @return 주문 코드 목록 응답 DTO 목록
     */
    List<GetPurchaseCodeListResponseDto> getPurchaseCodeList(String memberUuid);

    List<GetPurchaseDetailResponseDto> getPurchaseDetail(String memberUuid, String purchaseCode);
}
