package org.example.sivillage.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.review.application.ReviewServiceImpl;
import org.example.sivillage.review.dto.out.ReviewResponseDto;
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
    public BaseResponse<Long> addReview(@PathVariable("productUuid") String productUuid, @Valid @RequestBody ReviewRequestVo reviewRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        Long reviewId = reviewService.addReview(ReviewRequestVo.toDto(reviewRequestVo),authUserDetails.getMemberUuid(),productUuid); // vo -> dto
        return new BaseResponse<>(reviewId);
    }

    // 회원의 리뷰 아이디 리스트 api 회원 uuid 던졋을때 리뷰 아이디 리스트 나오게

    // 상품의 리뷰 아이디 리스트 api 상품 uuid 던졌을 때 리뷰 아이디 리스트 나오게


//    @Operation(summary = "리뷰 조회", description = "회원 리뷰를 조회합니다.")
//    @GetMapping("")
//    public BaseResponse<List<ReviewResponseVo>> getMemberReview(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
//        List<ReviewResponseVo> reviewResponseVoList = reviewService.getMemberReview(authUserDetails.getMemberUuid())
//                .stream()
//                .map(ReviewResponseDto::toResponseVo).toList();
//        return new BaseResponse<>(reviewResponseVoList);
//    }

    @Operation(summary = "리뷰 조회", description = "상품 리뷰를 조회합니다.")
    @GetMapping("")
    public BaseResponse<List<ReviewResponseVo>> getReview (@PathVariable("reviewId") Long reviewId) {
        List<ReviewResponseVo> reviewResponseVoList = reviewService.getProductReview(reviewId)
                .stream()
                .map(ReviewResponseDto::toResponseVo).toList();
        return new BaseResponse<>(reviewResponseVoList);
    }

    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.")
    @PutMapping("/{reviewId}")
    public BaseResponse<Void> changeReview(@PathVariable("reviewId") Long reviewId, @Valid @RequestBody ReviewRequestVo reviewRequestVo) {
        reviewService.changeReview(ReviewRequestVo.toDto(reviewRequestVo), reviewId);
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.")
    @DeleteMapping("/{reviewId}")
    public BaseResponse<Void> removeReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.removeReview(reviewId);
        return new BaseResponse<>();
    }
}
