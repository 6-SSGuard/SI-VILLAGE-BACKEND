package org.example.sivillage.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.member.application.BrandLikeService;
import org.example.sivillage.member.application.MemberService;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.member.application.ReviewLikeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 관리 API", description = "회원 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final BrandLikeService brandLikeService;
    private final ProductLikeService productLikeService;
    private final ReviewLikeService reviewLikeService;
    private final MemberService memberService;

    @Operation(summary = "브랜드 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/brand/like/{brandId}")
    public BaseResponse<Void> toggleBrandLike(@PathVariable Long brandId, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        brandLikeService.toggleBrandLike(brandId, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/product/like/{productUuid}")
    public BaseResponse<Void> toggleProductLike(@PathVariable String productUuid, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        productLikeService.toggleProductLike(productUuid, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/review/like/{reviewId}")
    public BaseResponse<Void> toggleReviewLike(@PathVariable Long reviewId, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        reviewLikeService.toggleReviewLike(reviewId, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "회원 이메일 조회", description = "회원 이메일 조회용(리뷰, 상품문의 사용예정)")
    @GetMapping("")
    public BaseResponse<String> getEmail(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        String email = memberService.getMemberEmail(authUserDetails.getMemberUuid());
        return new BaseResponse<>(email);
    }
}















