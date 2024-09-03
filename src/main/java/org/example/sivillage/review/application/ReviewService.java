package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.BeautyInfo.infrastructure.BeautyInfoRepository;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.product.vo.GetCategoryPathResponseVo;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.ReviewRequestDto;
import org.example.sivillage.review.dto.ReviewResponseDto;
import org.example.sivillage.review.infrastructure.ReviewImageRepository;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.example.sivillage.sizeinfo.infrastructure.SizeInfoRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final BeautyInfoRepository beautyInfoRepository;
    private final SizeInfoRepository sizeInfoRepository;


    // 리뷰 목록 가져오는 메소드
    public List<ReviewResponseDto> getReviewList(List<Review> reviews) {
        if (reviews.isEmpty()) {
            return Collections.emptyList();
        }
        // 리뷰 있는 경우
        return reviews.stream()
                .map(review -> {
                    // 리뷰 이미지 조회
                    List<ReviewImage> reviewImages = reviewImageRepository.findByReview(review);

                    // 이미지 URL 목록으로 변환
                    List<String> images = reviewImages.stream()
                            .map(ReviewImage::getReviewImageUrl)
                            .toList();
                    return ReviewResponseDto.toDto(review, images);
                }).toList();
    }

    public void addReview(ReviewRequestDto dto, String authorEmail, String memberUuid, String productUuid) {

        // 리뷰 저장
        Review review = Review.toEntity(dto, authorEmail, memberUuid, productUuid);
        reviewRepository.save(review);
        if (dto.getReviewImageUrl() != null) { // 리뷰 이미지는 리뷰이미지 테이블에 저장
            dto.getReviewImageUrl().forEach(images -> {
                ReviewImage image = ReviewImage.toEntity(images, review);
                reviewImageRepository.save(image);
            });
        }

        // 카테고리 별 개인정보 리뷰에 저장
        String url = "http://localhost:8080/api/product/category-path/" + productUuid;
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<BaseResponse<GetCategoryPathResponseVo>> typeRef =
                new ParameterizedTypeReference<BaseResponse<GetCategoryPathResponseVo>>() {
                };

        // HTTP GET 요청 및 응답 처리
        ResponseEntity<BaseResponse<GetCategoryPathResponseVo>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, typeRef);

        BaseResponse<GetCategoryPathResponseVo> response = responseEntity.getBody();
        if (response != null && response.isSuccess()) {
            GetCategoryPathResponseVo categoryList = response.getResult();

            if (categoryList != null) {
                // categoryPath 리스트를 가져오기
                List<String> categoryPath = categoryList.getCategoryPath();

                for (String category : categoryList.getCategoryPath()) {
                    if ("메이크업".equals(category)) {
                        review.toEntityMemberInfo(String.valueOf(beautyInfoRepository.findByMemberUuid(memberUuid).get().getSkinTone()));
                    } else if ("헤어케어".equals(category)) {
                        review.toEntityMemberInfo(String.valueOf(beautyInfoRepository.findByMemberUuid(memberUuid).get().getScalpTone()));
                    } else if ("뷰티".equals(category)) {
                        review.toEntityMemberInfo(String.valueOf(beautyInfoRepository.findByMemberUuid(memberUuid).get().getSkinType()));
                    } else if ("신발".equals(category)) {
                        review.toEntityMemberInfo(sizeInfoRepository.findByMemberUuid(memberUuid).get().getShoeSize());
                    } else {
                        review.toEntityMemberInfo(String.valueOf(sizeInfoRepository.findByMemberUuid(memberUuid).get().getTopSize()));
                    }
                }
            }
        }
    }


    // 회원 리뷰 조회
    public List<ReviewResponseDto> getMemberReview(String memberUuid){
        List<Review> reviews = reviewRepository.findByMemberUuid(memberUuid);
        return getReviewList(reviews);
    }

    // 상품별 리뷰 조회
    public List<ReviewResponseDto> getProductReview(String productUuid) {
        List<Review> reviews = reviewRepository.findByProductUuid(productUuid);
        return getReviewList(reviews);
    }

    public void changeReview(ReviewRequestDto dto, Long reviewId, String memberUuid) {
        reviewRepository.findByReviewIdAndMemberUuid(reviewId, memberUuid)
                .ifPresent(review -> {
                    review.change(dto);
                    reviewRepository.save(review);
                });
    }

    public void removeReview(Long reviewId, String memberUuid){
        reviewRepository.findByReviewIdAndMemberUuid(reviewId, memberUuid)
                .ifPresent(review -> {
                    reviewImageRepository.deleteAllByReview(review);
                    reviewRepository.delete(review);
                });
    }
}









