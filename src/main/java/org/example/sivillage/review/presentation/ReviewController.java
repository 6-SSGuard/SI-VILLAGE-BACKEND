package org.example.sivillage.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.review.application.ReviewServiceImpl;
import org.example.sivillage.review.vo.in.ReviewRequestVo;
import org.example.sivillage.review.vo.out.ReviewResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "리뷰 관리 API", description = "리뷰 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    @Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다.")
    @PostMapping("/{productUuid}")
    public BaseResponse<Void> addReview(@PathVariable("productUuid") String productUuid, @Valid @RequestBody ReviewRequestVo vo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        reviewService.addReview(ReviewRequestVo.toDto(vo), authUserDetails.getUsername(), authUserDetails.getMemberUuid(), productUuid); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary = "회원 리뷰 조회", description = "회원 리뷰를 조회합니다.")
    @GetMapping("")
    public BaseResponse<List<ReviewResponseVo>> getMemberReview(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        List<ReviewResponseVo> vo = reviewService.getMemberReview(authUserDetails.getMemberUuid())
                .stream()
                .map(ReviewResponseVo::toVo).toList();
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "상품 리뷰 조회", description = "상품 리뷰를 조회합니다.")
    @GetMapping("/product")
    public BaseResponse<List<ReviewResponseVo>> getReview(@PathVariable("productUuid") String productUuid) {
        List<ReviewResponseVo> vo = reviewService.getProductReview(productUuid)
                .stream()
                .map(ReviewResponseVo::toVo).toList();
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.")
    @PutMapping("/{reviewId}")
    public BaseResponse<Void> changeReview(@PathVariable("reviewId") Long reviewId, @Valid @RequestBody ReviewRequestVo vo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        reviewService.changeReview(ReviewRequestVo.toDto(vo), reviewId, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.")
    @DeleteMapping("/{reviewId}")
    public BaseResponse<Void> removeReview(@PathVariable("reviewId") Long reviewId, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        reviewService.removeReview(reviewId, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }
}
