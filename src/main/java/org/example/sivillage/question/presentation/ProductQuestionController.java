package org.example.sivillage.question.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.CustomUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.question.application.ProductQuestionService;
import org.example.sivillage.question.vo.in.ProductQuestionRequestVo;
import org.example.sivillage.question.vo.out.ProductQuestionResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품문의 관리 API", description = "문의 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product-question")
public class ProductQuestionController {

    private final ProductQuestionService productQuestionService;

    @Operation(summary = "상품문의 등록", description = "상품문의를 등록합니다.")
    @PostMapping("/{productUuid}")
    public BaseResponse<Void> addProductQuestion(@PathVariable("productUuid") String productUuid, @Valid @RequestBody ProductQuestionRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        productQuestionService.addProductQuestion(ProductQuestionRequestVo.toDto(vo), customUserDetails.getUsername(),productUuid,customUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "상품문의 조회", description = "상품 문의를 조회합니다.")
    @GetMapping("/{productUuid}")
    public BaseResponse<List<ProductQuestionResponseVo>> getProductQuestion(@PathVariable("productUuid") String productUuid) {
        List<ProductQuestionResponseVo> vo = productQuestionService.getProductQuestion(productUuid)
                .stream()
                .map(ProductQuestionResponseVo::toVo).toList();
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "회원 상품문의 조회", description = "회원의 상품 문의를 조회합니다.")
    @GetMapping("")
    public BaseResponse<List<ProductQuestionResponseVo>> getMemberUuidProductQuestion(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<ProductQuestionResponseVo> vo = productQuestionService.getMemberProductQuestion(customUserDetails.getMemberUuid())
                .stream()
                .map(ProductQuestionResponseVo::toVo).toList();
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "회원 상품문의 삭제", description = "회원의 상품 문의를 삭제합니다.")
    @DeleteMapping("/{productQuestionId}")
    public BaseResponse<List<ProductQuestionResponseVo>> removeProductQuestion(@PathVariable("productQuestionId") Long productQuestionId,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        productQuestionService.removeProductQuestion(productQuestionId, customUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

}
