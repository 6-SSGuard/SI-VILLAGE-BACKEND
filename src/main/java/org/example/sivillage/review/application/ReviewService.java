package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;
import org.example.sivillage.BeautyInfo.infrastructure.BeautyInfoRepository;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.ReviewRequestDto;
import org.example.sivillage.review.dto.ReviewResponseDto;
import org.example.sivillage.review.infrastructure.ReviewImageRepository;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.example.sivillage.sizeinfo.domain.SizeInfo;
import org.example.sivillage.sizeinfo.infrastructure.SizeInfoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final BeautyInfoRepository beautyInfoRepository;
    private final SizeInfoRepository sizeInfoRepository;
    private final ProductService productService;


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

        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid).orElse(new BeautyInfo());
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid).orElse(new SizeInfo());

        String categoryPath = productService.getCategoryPath(productUuid).getCategoryPath();

        Review review = Review.toEntity(dto, authorEmail, memberUuid, productUuid);

        String info = "";

        if (categoryPath.contains("뷰티")) {
            info = beautyInfo.getSkinType() != null ? String.valueOf(beautyInfo.getSkinType()) : ""; }
        if (categoryPath.contains("메이크업")) {
            info = beautyInfo.getSkinTone() != null ? String.valueOf(beautyInfo.getSkinTone()) : ""; }
        if (categoryPath.contains("헤어케어")) {
            info = beautyInfo.getScalpTone() != null ? String.valueOf(beautyInfo.getScalpTone()) : ""; }
        if (categoryPath.contains("슈즈")) {
            info = sizeInfo.getShoeSize() != null ? sizeInfo.getShoeSize() : ""; }

        if (!categoryPath.contains("뷰티") && !categoryPath.contains("메이크업") && !categoryPath.contains("헤어케어") && !categoryPath.contains("슈즈")) {
            if (sizeInfo.getTopSize() != null) {
                info = "키: " + sizeInfo.getHeight() + "cm, 몸무게: " + sizeInfo.getWeight() + "kg, 평소 사이즈: " + sizeInfo.getTopSize();} else {
                info = ""; } }

        review.toEntityMemberInfo(info);
        reviewRepository.save(review);

            // 리뷰 이미지 테이블에 따로 저장
        if (dto.getReviewImageUrl() != null) {
            dto.getReviewImageUrl().forEach(images -> { ReviewImage image = ReviewImage.toEntity(images, review);
                    reviewImageRepository.save(image);});
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









