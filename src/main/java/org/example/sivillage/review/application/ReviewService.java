package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.BeautyInfo.infrastructure.BeautyInfoRepository;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.ReviewRequestDto;
import org.example.sivillage.review.dto.ReviewResponseDto;
import org.example.sivillage.review.infrastructure.ReviewImageRepository;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.example.sivillage.sizeinfo.infrastructure.SizeInfoRepository;
import org.springframework.stereotype.Service;

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
    private final ProductService productService;
//
//    public void addReview(ReviewRequestDto dto,  String authorEmail, String memberUuid, String productUuid) {
//
//        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid).orElse(new BeautyInfo());
//        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid).orElse(new SizeInfo());
//        Review review = Review.toEntity(dto, authorEmail, memberUuid, productUuid);
//
//        String info = CategoryType.fromCategoryPath(productService.getCategoryPath(productUuid).getCategoryPath())
//                        .getInfo(beautyInfo, sizeInfo);
//
//        review.toEntityMemberInfo(info);
//        reviewRepository.save(review);
//        saveReviewImage(dto,review);
//
//    }

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

    // 리뷰 목록 가져오는 메소드
    private List<ReviewResponseDto> getReviewList(List<Review> reviews) {
        return reviews.isEmpty() ? Collections.emptyList() : reviews.stream()
                .map(review -> {
                    List<String> images = reviewImageRepository.findByReview(review).stream()
                            .map(ReviewImage::getReviewImageUrl)
                            .toList();
                    return ReviewResponseDto.toDto(review, images);
                }).toList();
    }

    // 리뷰 이미지 테이블에 저장 메소드
    private void saveReviewImage (ReviewRequestDto dto, Review review){
            if (dto.getReviewImageUrl() != null) {
        dto.getReviewImageUrl().forEach(images -> { ReviewImage image = ReviewImage.toEntity(images, review);
            reviewImageRepository.save(image);});
    }

    }

}









