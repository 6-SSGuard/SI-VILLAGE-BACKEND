package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;
import org.example.sivillage.BeautyInfo.infrastructure.BeautyInfoRepository;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.product.dto.out.GetCategoryPathResponseDto;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.ReviewRequestDto;
import org.example.sivillage.review.dto.ReviewResponseDto;
import org.example.sivillage.review.infrastructure.ReviewImageRepository;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.example.sivillage.sizeinfo.domain.SizeInfo;
import org.example.sivillage.sizeinfo.infrastructure.SizeInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ProductService productService;
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
                }).toList(); }

    public void addReview(ReviewRequestDto dto, String authorEmail, String memberUuid, String productUuid) {

        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid).orElse(null);
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid).orElse(null);

        // 리뷰 객체 생성 및 저장
        Review review = Review.toEntity(dto, authorEmail, memberUuid, productUuid);
        reviewRepository.save(review);

        // 이미지 저장
        if (dto.getReviewImageUrl() != null) {
            dto.getReviewImageUrl().forEach(images -> {
                ReviewImage image = ReviewImage.toEntity(images, review);
                reviewImageRepository.save(image);
            });
        }

        // 정보가 있는 경우에만 처리
        if (sizeInfo != null || beautyInfo != null) {
            GetCategoryPathResponseDto categoryList = productService.getCategoryPath(productUuid);
            System.out.println(categoryList.getCategoryPath().get(0));
            Map<String, String> categoryToInfoMap = new HashMap<>();

            if (beautyInfo != null) {
                categoryToInfoMap.put("메이크업", String.valueOf(beautyInfo.getSkinTone()));
                categoryToInfoMap.put("헤어케어", String.valueOf(beautyInfo.getScalpTone()));
                categoryToInfoMap.put("뷰티", String.valueOf(beautyInfo.getSkinType()));
            }

            if (sizeInfo != null) {
                categoryToInfoMap.put("신발", sizeInfo.getShoeSize());
                categoryToInfoMap.put("기본", "키: " + sizeInfo.getHeight() +
                        "cm, 몸무게: " + sizeInfo.getWeight() +
                        "kg, 평소 사이즈: " + sizeInfo.getTopSize());
            }

            // 카테고리 목록에 맞는 리뷰 작성자 정보 설정
            for (String category : categoryList.getCategoryPath()) {
                System.out.println(categoryList.getCategoryPath());
                String memberInfo = categoryToInfoMap.get(category);
                if (memberInfo != null) {
                    review.toEntityMemberInfo(memberInfo);
                }
            }
            // 모든 정보가 설정된 후에 리뷰 저장
            reviewRepository.save(review);
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









