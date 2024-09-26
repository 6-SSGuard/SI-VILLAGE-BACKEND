package org.example.sivillage.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.review.application.ReviewLikeService;
import org.example.sivillage.review.dto.out.ReviewLikeCountResponseDto;
import org.example.sivillage.review.vo.out.ReviewLikeCountResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰 좋아요 관리 API", description = "리뷰 좋아요 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/review-like")
@Tag(name = "찜하기")
public class ReviewLikeController {

    private final ReviewLikeService reviewLikeService;

    // 리뷰 좋아요 관련 API
    @Operation(summary = "리뷰 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/member/{reviewId}/like")
    public BaseResponse<Void> toggleReviewLike(@PathVariable Long reviewId, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        reviewLikeService.toggleReviewLike(reviewId, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰의 좋아요 조회", description = "리뷰의 좋아요 수 조회")
    @GetMapping("/member/{reviewId}/like")
    public BaseResponse<ReviewLikeCountResponseVo> getReviewLikeCount(@PathVariable("reviewId") Long reviewId){
        ReviewLikeCountResponseDto reviewLikeCountResponseDto = reviewLikeService.getReviewLikeCount(reviewId);
        return new BaseResponse<>(reviewLikeCountResponseDto.toResponseVo());
    }

}
