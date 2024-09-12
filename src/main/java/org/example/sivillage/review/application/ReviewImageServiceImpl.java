package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.in.ReviewImageRequestDto;
import org.example.sivillage.review.dto.out.ReviewImageResponseDto;
import org.example.sivillage.review.infrastructure.ReviewImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewImageServiceImpl implements ReviewImageService{

    private final ReviewImageRepository reviewImageRepository;

    // 등록
    public void addReviewImage(ReviewImageRequestDto reviewImageRequestDto, Long reviewId) {
        for (String imageUrl : reviewImageRequestDto.getReviewImageUrl()) {
            ReviewImage reviewImage = ReviewImageRequestDto.toEntity(imageUrl, reviewId);
            reviewImageRepository.save(reviewImage);
        }
    }

    // 조회
    public List<ReviewImageResponseDto> getReviewImage(Long reviewId) {
        return reviewImageRepository.findByReviewId(reviewId)
                .stream()
                .map(ReviewImageResponseDto::from)
                .toList();
    }

    public void removeReviewImage(Long reviewImageId) {
        reviewImageRepository.deleteById(reviewImageId);
    }
}
