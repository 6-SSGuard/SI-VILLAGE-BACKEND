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
    public void addReviewImage(List<ReviewImageRequestDto> reviewImageRequestDto, Long reviewId) {
        List<ReviewImage> reviewImages = reviewImageRequestDto.stream()
                .map(dto -> ReviewImageRequestDto.toEntity(dto.getReviewImageUrl(), reviewId))
                .toList();
        reviewImageRepository.saveAll(reviewImages);
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
