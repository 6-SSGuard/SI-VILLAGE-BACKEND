package org.example.sivillage.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;
import org.example.sivillage.review.application.ReviewImageServiceImpl;
import org.example.sivillage.review.application.ReviewServiceImpl;
import org.example.sivillage.review.application.ReviewSortServiceImpl;
import org.example.sivillage.review.dto.in.ReviewImageRequestDto;
import org.example.sivillage.review.dto.out.ReviewImageResponseDto;
import org.example.sivillage.review.dto.out.ReviewResponseDto;
import org.example.sivillage.review.vo.in.ReviewImageRequestVo;
import org.example.sivillage.review.vo.in.ReviewRequestVo;
import org.example.sivillage.review.vo.out.ReviewImageResponseVo;
import org.example.sivillage.review.vo.out.ReviewResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "리뷰 관리 API", description = "리뷰 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewServiceImpl reviewService;
    private final ReviewImageServiceImpl reviewImageService;
    private final ReviewSortServiceImpl reviewSortService;

    @Operation(summary = "상품의 리뷰id 조회", description = "상품의 리뷰 id 리스트를 반환")
    @GetMapping("/product/{productCode}")
    public BaseResponse<List<IdListResponseVo<Long>>> getProductReviewIds(@PathVariable("productCode") String productCode) {
        List<IdListResponseVo<Long>> idListResponseVoList = reviewService.getProductReviewIds(productCode)
                .stream()
                .map(IdListResponseDto::toVo)
                .toList();
        return new BaseResponse<>(idListResponseVoList);
    }

    @Operation(summary = "회원의 리뷰id 조회", description = "회원의 리뷰 id 리스트를 반환", tags = "마이페이지-나의 활동 정보")
    @GetMapping("/member")
    public BaseResponse<List<IdListResponseVo<Long>>> getMemberReviewIds(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        List<IdListResponseVo<Long>> idListResponseVoList = reviewService.getMemberReviewIds(authUserDetails.getMemberUuid())
                .stream()
                .map(IdListResponseDto::toVo)
                .toList();
        return new BaseResponse<>(idListResponseVoList);
    }

    @Operation(summary = "리뷰 정렬",
            description = """
            
            리뷰 목록은 페이징 처리되며, 사용자는 `sort` 파라미터를 통해 정렬 기준을 선택할 수 있습니다.
            페이징 처리된 리뷰의 마지막 ID를 기준으로 커서 페이징이 적용됩니다.

            만약 `lastReviewId`가 주어지지 않으면, 첫 페이지를 조회합니다.

            sort 파라미터는 다음과 같은 옵션을 제공합니다:
            - `created`: 리뷰 생성일 기준으로 내림차순 정렬
            - `likes`: 리뷰 좋아요 개수를 기준으로 내림차순 정렬
            - `best`: 평점을 기준으로 오름차순 정렬
            """)
    @GetMapping("/sort")
    public BaseResponse<List<IdListResponseVo<Long>>> getSortReviews (@RequestParam(required = false) Long lastReviewId,  @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "created") String sort) {
        List<IdListResponseVo<Long>> idListResponseVoList = reviewSortService.getSortReviews(lastReviewId, pageSize, sort)
                .stream()
                .map(IdListResponseDto::toVo)
                .toList();
        return new BaseResponse<>(idListResponseVoList);
    }


    @Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다.")
    @PostMapping("/member/{productCode}")
    public BaseResponse<Long> addReview(@PathVariable("productCode") String productCode, @Valid @RequestBody ReviewRequestVo reviewRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        Long reviewId = reviewService.addReview(ReviewRequestVo.toDto(reviewRequestVo),authUserDetails.getMemberUuid(),productCode); // vo -> dto
        return new BaseResponse<>(reviewId);
    }

    @Operation(summary = "리뷰 조회", description = "상품 리뷰를 조회합니다.")
    @GetMapping("/{reviewId}")
    public BaseResponse<ReviewResponseVo> getReview (@PathVariable("reviewId") Long reviewId) {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId);
        return new BaseResponse<>(reviewResponseDto.toResponseVo());
    }

    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.")
    @PutMapping("/member/{reviewId}")
    public BaseResponse<Void> changeReview(@PathVariable("reviewId") Long reviewId, @Valid @RequestBody ReviewRequestVo reviewRequestVo) {
        reviewService.changeReview(ReviewRequestVo.toDto(reviewRequestVo), reviewId);
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.")
    @DeleteMapping("/member/{reviewId}")
    public BaseResponse<Void> removeReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.removeReview(reviewId);
        return new BaseResponse<>();
    }

    @Operation(summary = "CSV 파일로 리뷰 추가", tags = {"admin-pre-data"})
    @PostMapping(value = "/admin/csv", consumes = "multipart/form-data")
    public BaseResponse<Void> addReviewCSV(@RequestParam("file") MultipartFile file) {
        reviewService.addReviewFromCsv(file);
        return new BaseResponse<>();
    }


    // 리뷰 이미지 관련 API
    @Operation(summary = "리뷰 이미지 등록", description = "리뷰 이미지를 등록합니다.")
    @PostMapping("/member/{reviewId}/images")
    public BaseResponse<Void> addReview(@PathVariable("reviewId") Long reviewId, @Valid @RequestBody List<ReviewImageRequestVo> reviewImageRequestVo) {
        List<ReviewImageRequestDto> reviewImageRequestDtoList = reviewImageRequestVo.stream()
                        .map(ReviewImageRequestVo::toDto)
                                .toList();

        reviewImageService.addReviewImage(reviewImageRequestDtoList, reviewId);
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰 이미지 목록 조회", description = "리뷰 이미지 목록을 조회합니다.")
    @GetMapping("/{reviewId}/images")
    public BaseResponse<List<ReviewImageResponseVo>> getReviewImages(@PathVariable("reviewId") Long reviewId) {
        List<ReviewImageResponseVo> reviewImageResponseVoList = reviewImageService.getReviewImage(reviewId)
                .stream()
                .map(ReviewImageResponseDto::toResponseVo)
                .toList();
        return new BaseResponse<>(reviewImageResponseVoList);
    }

    @Operation(summary = "리뷰 이미지 삭제", description = "리뷰 이미지를 삭제합니다.")
    @DeleteMapping("/member/{reviewImageId}/images")
    public BaseResponse<Void> removeReviewImage(@PathVariable("reviewImageId") Long reviewImageId) {
        reviewImageService.removeReviewImage(reviewImageId);
        return new BaseResponse<>();
    }

    @Operation(summary = "CSV 파일로 리뷰 이미지 추가", tags = {"admin-pre-data"})
    @PostMapping(value = "/admin/csv/Image", consumes = "multipart/form-data")
    public BaseResponse<Void> addReviewImageCSV(@RequestParam("file") MultipartFile file) {
        reviewImageService.addReviewImageFromCsv(file);
        return new BaseResponse<>();
    }
}
