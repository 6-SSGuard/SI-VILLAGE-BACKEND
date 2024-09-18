package org.example.sivillage.question.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.question.application.ProductQuestionAnswerServiceImpl;
import org.example.sivillage.question.dto.out.ProductQuestionAnswerResponseDto;
import org.example.sivillage.question.vo.in.ProductQuestionAnswerRequestVo;
import org.example.sivillage.question.vo.out.ProductQuestionAnswerResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품문의 답변 관리 API", description = "문의 답변 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product-question-answer")
public class ProductQuestionAnswerController {

    private final ProductQuestionAnswerServiceImpl productQuestionAnswerService;

    @Operation(summary = "상품문의 답변 등록", description = "상품문의 답변을 등록합니다.")
    @PostMapping("/{productQuestionId}")
    public BaseResponse<Void> addProductQuestionAnswer(@PathVariable("productQuestionId") Long productQuestionId, @Valid @RequestBody ProductQuestionAnswerRequestVo productQuestionAnswerRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        productQuestionAnswerService.addProductQuestionAnswer(ProductQuestionAnswerRequestVo.toDto(productQuestionAnswerRequestVo),productQuestionId, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "상품문의 답변 조회", description = "상품문의 답변을 조회합니다.")
    @GetMapping("/{productQuestionId}")
    public BaseResponse<ProductQuestionAnswerResponseVo> getProductQuestionAnswer(@PathVariable("productQuestionId") Long productQuestionId) { // 상품 문의 id로 조회
        ProductQuestionAnswerResponseDto productQuestionResponseAnswerDto = productQuestionAnswerService.getProductQuestionAnswer(productQuestionId);
        return new BaseResponse<>(productQuestionResponseAnswerDto.toResponseVo());

    }

    @Operation(summary = "판매자의 상품문의답변 조회", description = "판매자의 상품문의 답변을 조회합니다.")
    @GetMapping("")
    public BaseResponse<List<ProductQuestionAnswerResponseVo>> getVendorProductQuestionAnswer(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        List<ProductQuestionAnswerResponseVo> productQuestionAnswerResponseVoList = productQuestionAnswerService.getVendorProductQuestionAnswer(authUserDetails.getMemberUuid())
                .stream()
                .map(ProductQuestionAnswerResponseDto::toResponseVo).toList();
        return new BaseResponse<>(productQuestionAnswerResponseVoList);
    }

}
