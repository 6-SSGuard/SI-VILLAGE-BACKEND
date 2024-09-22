package org.example.sivillage.purchase.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.util.SessionUtils;
import org.example.sivillage.purchase.application.PayService;
import org.example.sivillage.purchase.application.PurchaseService;
import org.example.sivillage.purchase.dto.in.AddPurchaseFromCartRequestDto;
import org.example.sivillage.purchase.dto.in.AddPurchaseRequestDto;
import org.example.sivillage.purchase.dto.out.ApproveResponse;
import org.example.sivillage.purchase.dto.out.ReadyResponse;
import org.example.sivillage.purchase.vo.in.AddPurchaseFromCartRequestVo;
import org.example.sivillage.purchase.vo.in.AddPurchaseRequestVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@Tag(name = "상품 구매", description = "상품 구매 관련 API")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PayService payService;

    @PostMapping("/pay/ready")
    public ReadyResponse payReady() {

        String name = "민지훈";
        int totalPrice = 20000;

        log.info("name: {}, totalPrice: {}", name, totalPrice);

        // 카카오 결제 준비
        ReadyResponse readyResponse = payService.payReady(name, totalPrice);
        // 세션에 결제 고유변호(tid) 저장
        SessionUtils.addAttribute("tid", readyResponse.getTid());
        log.info("결제 고유변호(tid): {}", readyResponse.getTid());

        return readyResponse;
    }

    @GetMapping("/pay/completed")
    public ModelAndView payCompleted(@RequestParam("pg_token") String pgToken) {

        String tid = (String) SessionUtils.getAttribute("tid");
        log.info("결제 고유변호(tid): {}", tid);
        log.info("결제 승인 요청을 인증하는 토큰: {}", pgToken);

        // 카카오 결제 요청
        ApproveResponse approveResponse = payService.payApprove(tid, pgToken);

        ModelAndView modelAndView = new ModelAndView();
        // ModelAndView 객체를 사용하여 뷰 이름과 모델 데이터 설정
        modelAndView.setViewName("pay/completed");
        modelAndView.addObject("message", "결제가 성공적으로 완료되었습니다.");
        modelAndView.addObject("details", approveResponse);

        return modelAndView;
    }

    @GetMapping("/pay/completed/success")
    public String payCompletedSuccess(Model model) {
        model.addAttribute("message", "결제가 성공적으로 완료되었습니다.");
        return "pay/completed";
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
