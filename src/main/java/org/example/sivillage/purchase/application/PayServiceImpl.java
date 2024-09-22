package org.example.sivillage.purchase.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.purchase.domain.PurchaseProduct;
import org.example.sivillage.purchase.dto.in.KakaoPayRequestDto;
import org.example.sivillage.purchase.dto.out.ApproveResponse;
import org.example.sivillage.purchase.dto.out.ReadyResponse;
import org.example.sivillage.purchase.infrastructure.PurchaseProductRepository;
import org.example.sivillage.purchase.infrastructure.PurchaseRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PayServiceImpl implements PayService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public ReadyResponse payReady(String memberUuid, KakaoPayRequestDto kakaoPayRequestDto) {

        String email = memberRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND))
                .getEmail();

        int totalPrice = purchaseRepository.findByPurchaseCode(kakaoPayRequestDto.getPurchaseCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PURCHASE))
                .getTotalPriceAfterDiscount();

        List<PurchaseProduct> purchaseProducts = purchaseProductRepository.findByPurchaseCode(kakaoPayRequestDto.getPurchaseCode());

        String itemName = getItemName(purchaseProducts);

        int totalQuantity = purchaseProducts.stream()
            .mapToInt(PurchaseProduct::getAmount)
                .sum();

        // 카카오페이 결제창 연결

        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");                                    // 가맹점 코드(테스트용)
        parameters.put("partner_order_id", kakaoPayRequestDto.getPurchaseCode());    // 주문번호
        parameters.put("partner_user_id", email);                          // 회원 아이디
        parameters.put("item_name", itemName);                                      // 상품명
        parameters.put("quantity", String.valueOf(totalQuantity));                                        // 상품 수량
        parameters.put("total_amount", String.valueOf(totalPrice));             // 상품 총액
        parameters.put("tax_free_amount", "0");                                 // 상품 비과세 금액
        parameters.put("approval_url", "http://localhost:8080/api/purchase/pay/completed?partner_order_id="
                + kakaoPayRequestDto.getPurchaseCode() + "&partner_user_id=" + email); // 결제 성공 시 URL
        parameters.put("cancel_url", "http://localhost:8080/api/purchase/pay/cancel");      // 결제 취소 시 URL
        parameters.put("fail_url", "http://localhost:8080/api/purchase/pay/fail");          // 결제 실패 시 URL

        // HttpEntity : HTTP 요청 또는 응답에 해당하는 Http Header와 Http Body를 포함하는 클래스
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // RestTemplate
        // : Rest 방식 API를 호출할 수 있는 Spring 내장 클래스
        //   REST API 호출 이후 응답을 받을 때까지 기다리는 동기 방식 (json, xml 응답)
        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
        // RestTemplate의 postForEntity : POST 요청을 보내고 ResponseEntity로 결과를 반환받는 메소드
        ResponseEntity<ReadyResponse> responseEntity = template.postForEntity(url, requestEntity, ReadyResponse.class);
        log.info("결제준비 응답객체: {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    private String getItemName(List<PurchaseProduct> purchaseProducts) {

        String productName = productRepository.findByProductCode(purchaseProducts.get(0).getProductCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT))
                .getProductName();

        if (purchaseProducts.size() > 1) {
            return productName + " 외 " + (purchaseProducts.size() - 1) + "개 상품";
        }
        return productName;
    }

    // 카카오페이 결제 승인
    // 사용자가 결제 수단을 선택하고 비밀번호를 입력해 결제 인증을 완료한 뒤,
    // 최종적으로 결제 완료 처리를 하는 단계
    @Override
    public ApproveResponse payApprove(String tid, String pgToken, String partner_order_id, String partner_user_id) {
        HttpEntity<Map<String, String>> requestEntity = getMapHttpEntity(tid, pgToken, partner_order_id, partner_user_id);

        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        log.info("결제승인 응답객체: " + approveResponse);

        // DB에 저장하는 비즈니스 로직 추가

        return approveResponse;
    }

    private HttpEntity<Map<String, String>> getMapHttpEntity(String tid, String pgToken,
                                                 String partner_order_id, String partner_user_id) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");              // 가맹점 코드(테스트용)
        parameters.put("tid", tid);                       // 결제 고유번호
        parameters.put("partner_order_id", partner_order_id); // 주문번호
        parameters.put("partner_user_id", partner_user_id);    // 회원 아이디
        parameters.put("pg_token", pgToken);              // 결제승인 요청을 인증하는 토큰

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        return requestEntity;
    }

    // 카카오페이 측에 요청 시 헤더부에 필요한 값
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY DEV471B80D962552D2BE006DC30F2B468696E30B");
        headers.set("Content-type", "application/json");

        return headers;
    }
}
