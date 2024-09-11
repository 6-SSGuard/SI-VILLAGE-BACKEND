package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void addReviewImage(ReviewImageRequestDto reviewImageRequestDto, String memberUuid) {


    }

    @Override
    public List<ReviewImageResponseDto> getReviewImage(Long reviewId) {
        return List.of();
    }

    @Override
    public void changeReviewImage(ReviewImageResponseDto reviewImageResponseDto, Long reviewId) {

    }

    @Override
    public void removeReviewImage(Long id) {

    }
}
