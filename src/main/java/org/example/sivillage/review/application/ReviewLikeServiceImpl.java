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
                .orElse(reviewLikeRepository.save(ReviewLike.toEntity(memberUuid, reviewId)));

        // 값이 있다면
        reviewLike.toggleLike(); // 좋아요 해제
         // 좋아요 해제
        reviewLikeRepository.save(reviewLike);
    }

    public ReviewLikeCountResponseDto getReviewLikeCount(Long reviewId){
       return ReviewLikeCountResponseDto.from(reviewLikeRepository.countByReviewId(reviewId));
    }

}
