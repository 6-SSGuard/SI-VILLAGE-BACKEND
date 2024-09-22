package org.example.sivillage.purchase.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.purchase.application.PurchaseService;
import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;
import org.example.sivillage.purchase.dto.out.GetPurchaseCodeListResponseDto;
import org.example.sivillage.purchase.dto.out.GetPurchaseDetailResponseDto;
import org.example.sivillage.purchase.vo.in.AddPurchaseFromCartRequestVo;
import org.example.sivillage.purchase.vo.in.AddPurchaseRequestVo;
import org.example.sivillage.purchase.vo.out.GetPurchaseCodeListResponseVo;
import org.example.sivillage.purchase.vo.out.GetPurchaseDetailResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@Tag(name = "상품 구매", description = "상품 구매 관련 API")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "회원의 주문 code 리스트 조회")
    @GetMapping("/code-list")
    public BaseResponse<List<GetPurchaseCodeListResponseVo>> getPurchaseCodeList(@AuthenticationPrincipal AuthUserDetails authUserDetails) {

        return new BaseResponse<>(
                purchaseService.getPurchaseCodeList(authUserDetails.getMemberUuid())
                        .stream()
                        .map(GetPurchaseCodeListResponseDto::toVo)
                        .toList()
        );
    }

    @Operation(summary = "회원의 주문 상세 조회")
    @GetMapping("/{purchaseCode}")
    public BaseResponse<List<GetPurchaseDetailResponseVo>> getPurchaseDetail(@AuthenticationPrincipal AuthUserDetails authUserDetails,
                                                                                                @PathVariable String purchaseCode) {
        return new BaseResponse<>(
                purchaseService.getPurchaseDetail(authUserDetails.getMemberUuid(), purchaseCode)
                        .stream().map(GetPurchaseDetailResponseDto::toVo)
                        .toList()
        );
    }


    @Operation(summary = "단일 상품 구매", description = "상품 상세 페이지에서 직접적으로 단일 상품을 구매하는 api")
    @PostMapping
    public BaseResponse<Void> addPurchase(@AuthenticationPrincipal AuthUserDetails authUserDetails,
                                          @RequestBody AddPurchaseRequestVo addPurchaseRequestVo) {
        purchaseService.addPurchase(authUserDetails.getMemberUuid(),
                AddPurchaseRequestDto.from(addPurchaseRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "장바구니 상품 구매", description = "장바구니에 담긴 상품 중 선택된 것들을 골라 한번에 구매하는 api")
    @PostMapping("/cart")
    public BaseResponse<Void> addPurchaseFromCart(@AuthenticationPrincipal AuthUserDetails authUserDetails,
                                                  @RequestBody AddPurchaseFromCartRequestVo addPurchaseFromCartRequestVo) {

        purchaseService.addPurchaseFromCart(authUserDetails.getMemberUuid(),
                AddPurchaseFromCartRequestDto.from(addPurchaseFromCartRequestVo));
        return new BaseResponse<>();
    }
}
