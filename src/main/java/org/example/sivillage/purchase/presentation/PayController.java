package org.example.sivillage.purchase.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.util.SessionUtils;
import org.example.sivillage.purchase.application.PayService;
import org.example.sivillage.purchase.dto.in.KakaoPayRequestDto;
import org.example.sivillage.purchase.dto.out.ApproveResponse;
import org.example.sivillage.purchase.dto.out.ReadyResponse;
import org.example.sivillage.purchase.vo.in.KakaoPayRequestVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pay")
@Tag(name = "상품 결제", description = "상품 주문 이후 결제 관련 API")
public class PayController {

    private final PayService payService;

    @Operation(summary = "카카오 결제 준비", description = "카카오 결제 준비 API")
    @PostMapping("/ready")
    public ReadyResponse payReady(@AuthenticationPrincipal AuthUserDetails authUserDetails,
                                  @RequestBody KakaoPayRequestVo kakaoPayRequestVo) {

        // 카카오 결제 준비
        ReadyResponse readyResponse = payService.payReady(authUserDetails.getMemberUuid(),
                KakaoPayRequestDto.from(kakaoPayRequestVo));

        // 세션에 결제 고유변호(tid) 저장
        SessionUtils.addAttribute("tid", readyResponse.getTid());
        log.info("결제 고유변호(tid): {}", readyResponse.getTid());

        return readyResponse;
    }

    @Operation(summary = "카카오 결제 승인", description = "카카오 결제 승인 API")
    @GetMapping("/completed")
    public ModelAndView payCompleted(@RequestParam("pg_token") String pgToken,
                                     @RequestParam("partner_order_id") String partner_order_id,
                                     @RequestParam("partner_user_id") String partner_user_id) {

        String tid = (String) SessionUtils.getAttribute("tid");
        log.info("결제 고유변호(tid): {}", tid);
        log.info("결제 승인 요청을 인증하는 토큰: {}", pgToken);

        // 카카오 결제 요청
        ApproveResponse approveResponse = payService.payApprove(tid, pgToken, partner_order_id, partner_user_id);

        ModelAndView modelAndView = new ModelAndView();
        // ModelAndView 객체를 사용하여 뷰 이름과 모델 데이터 설정
        modelAndView.setViewName("pay/completed");
        modelAndView.addObject("message", "결제가 성공적으로 완료되었습니다.");
        modelAndView.addObject("details", approveResponse);

        return modelAndView;
    }

    @Operation(summary = "카카오 결제 취소", description = "카카오 결제 취소 API")
    @GetMapping("/cancel")
    public ModelAndView payCancel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pay/cancel");
        modelAndView.addObject("message", "결제가 취소되었습니다.");
        return modelAndView;
    }

    @Operation(summary = "카카오 결제 실패", description = "카카오 결제 실패 API")
    @GetMapping("/fail")
    public ModelAndView payFail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pay/fail");
        modelAndView.addObject("message", "결제 중에 오류가 발생했습니다.");
        return modelAndView;
    }
}
