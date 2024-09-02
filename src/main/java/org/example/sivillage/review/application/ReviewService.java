package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.ReviewRequestDto;
import org.example.sivillage.review.dto.ReviewResponseDto;
import org.example.sivillage.review.infrastructure.ReviewImageRepository;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    public void addReview(ReviewRequestDto dto, String authorEmail, String memberUuid, String productUuid) {
        Review review = Review.toEntity(dto, authorEmail, memberUuid, productUuid);
        reviewRepository.save(review);
        if (dto.getReviewImageUrl() != null) { // 리뷰 이미지는 리뷰이미지 테이블에 저장
            dto.getReviewImageUrl().forEach(url -> {
                ReviewImage image = ReviewImage.toEntity(url, review);
                reviewImageRepository.save(image);
            });
        }
    }

    // todo: productUuid 에서 카테고리 타입을 찾아서 그 타입에 맞는 사이즈, 뷰티 정보를 가져오기
    public List<ReviewResponseDto> getReview(String productUuid) {
        List<Review> reviews = reviewRepository.findByProductUuid(productUuid);

        // 리뷰가 없는 경우 빈 리스트를 반환
        if (reviews.isEmpty()) {
            return Collections.emptyList();}
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

    public void changeReview(ReviewRequestDto dto, Long reviewId, String memberUuid) {
        Review review = reviewRepository.findByReviewIdAndMemberUuid(reviewId, memberUuid).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_REVIEW));
        review.change(dto);
        reviewRepository.save(review);
    }

    public void removeReview(Long reviewId, String memberUuid){
        Review review = reviewRepository.findByReviewIdAndMemberUuid(reviewId, memberUuid).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_REVIEW));
        reviewImageRepository.deleteAllByReview(review);
        reviewRepository.delete(review);
    }
}









