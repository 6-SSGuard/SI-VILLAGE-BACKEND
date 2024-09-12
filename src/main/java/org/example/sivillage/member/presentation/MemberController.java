package org.example.sivillage.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.product.application.ProductLikeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 관리 API", description = "회원 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final ProductLikeService productLikeService;

    
    @Operation(summary = "상품 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/product/like/{productUuid}")
    public BaseResponse<Void> toggleProductLike(@PathVariable String productUuid, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        productLikeService.toggleProductLike(productUuid, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }
}















