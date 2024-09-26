package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.review.domain.ReviewLike;
import org.example.sivillage.review.dto.out.ReviewLikeCountResponseDto;
import org.example.sivillage.review.infrastructure.ReviewLikeRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class ReviewLikeServiceImpl implements ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    public void toggleReviewLike(Long reviewId, String memberUuid) {
        ReviewLike reviewLike = reviewLikeRepository.findByReviewIdAndMemberUuid(reviewId, memberUuid)
                .orElse(ReviewLike.toEntity(memberUuid, reviewId));

        reviewLike.toggleLike();

        reviewLikeRepository.save(reviewLike);
    }

    public ReviewLikeCountResponseDto getReviewLikeCount(Long reviewId){
       return ReviewLikeCountResponseDto.from(reviewLikeRepository.countByReviewIdAndLike(reviewId));
    }



}
