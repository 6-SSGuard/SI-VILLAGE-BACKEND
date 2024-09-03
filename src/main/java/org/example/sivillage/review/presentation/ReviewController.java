package org.example.sivillage.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.CustomUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.review.application.ReviewService;
import org.example.sivillage.review.vo.ReviewRequestVo;
import org.example.sivillage.review.vo.ReviewResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "리뷰 관리 API", description = "리뷰 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다.")
    @PostMapping("/{productUuid}")
    public BaseResponse<Void> addReview(@PathVariable("productUuid") String productUuid, @Valid @RequestBody ReviewRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        reviewService.addReview(ReviewRequestVo.toDto(vo), customUserDetails.getUsername(), customUserDetails.getMemberUuid(), productUuid); // vo -> dto
        return new BaseResponse<>();
    }



    @Operation(summary = "회원 리뷰 조회", description = "회원 리뷰를 조회합니다.")
    @GetMapping("")
    public BaseResponse<List<ReviewResponseVo>> getMemberReview(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<ReviewResponseVo> vo = reviewService.getMemberReview(customUserDetails.getMemberUuid())
                .stream()
                .map(ReviewResponseVo::toVo).toList();
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "상품 리뷰 조회", description = "상품 리뷰를 조회합니다.")
    @GetMapping("/product")
    public BaseResponse<List<ReviewResponseVo>> getReview(@RequestParam("productUuid") String productUuid) {
        List<ReviewResponseVo> vo = reviewService.getProductReview(productUuid)
                .stream()
                .map(ReviewResponseVo::toVo).toList();
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.")
    @PutMapping("/{reviewId}")
    public BaseResponse<Void> changeReview(@PathVariable("reviewId") Long reviewId, @Valid @RequestBody ReviewRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        reviewService.changeReview(ReviewRequestVo.toDto(vo), reviewId, customUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.")
    @DeleteMapping("/{reviewId}")
    public BaseResponse<Void> removeReview(@PathVariable("reviewId") Long reviewId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        reviewService.removeReview(reviewId, customUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }
}
