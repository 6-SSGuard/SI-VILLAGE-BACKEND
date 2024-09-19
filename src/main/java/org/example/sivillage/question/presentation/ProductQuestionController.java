package org.example.sivillage.question.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.question.application.ProductQuestionServiceImpl;
import org.example.sivillage.question.dto.out.ProductQuestionResponseDto;
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

    private final ProductQuestionServiceImpl productQuestionService;

    // todo: memberUuid 로 email 가져오는 api 만들기 (상품문의,리뷰등록시 사용하기 위해서)
    @Operation(summary = "상품문의 등록", description = "상품문의를 등록합니다.")
    @PostMapping("/{productUuid}")
    public BaseResponse<Void> addProductQuestion(@PathVariable("productUuid") String productUuid, @Valid @RequestBody ProductQuestionRequestVo productQuestionRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        productQuestionService.addProductQuestion(ProductQuestionRequestVo.toDto(productQuestionRequestVo), productUuid, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "상품문의 조회", description = "상품 문의를 조회합니다.")
    @GetMapping("/{productUuid}")
    public BaseResponse<List<ProductQuestionResponseVo>> getProductQuestion(@PathVariable("productUuid") String productUuid) {
        List<ProductQuestionResponseDto> productQuestionResponseDtoList = productQuestionService.getProductQuestion(productUuid);
        return new BaseResponse<>(
                productQuestionResponseDtoList.stream()
                        .map(ProductQuestionResponseDto::toResponseVo).toList());
    }

    @Operation(summary = "회원 상품문의 조회", description = "회원의 상품 문의를 조회합니다.")
    @GetMapping("")
    public BaseResponse<List<ProductQuestionResponseVo>> getMemberUuidProductQuestion(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        List<ProductQuestionResponseVo> productQuestionResponseVoList = productQuestionService.getMemberProductQuestion(authUserDetails.getMemberUuid())
                .stream()
                .map(ProductQuestionResponseDto::toResponseVo).toList();
        return new BaseResponse<>(productQuestionResponseVoList);
    }

    @Operation(summary = "회원 상품문의 삭제", description = "회원의 상품 문의를 삭제합니다.")
    @DeleteMapping("/{productQuestionId}")
    public BaseResponse<List<ProductQuestionResponseVo>> removeProductQuestion(@PathVariable("productQuestionId") Long productQuestionId,@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        productQuestionService.removeProductQuestion(productQuestionId);
        return new BaseResponse<>();
    }

}
