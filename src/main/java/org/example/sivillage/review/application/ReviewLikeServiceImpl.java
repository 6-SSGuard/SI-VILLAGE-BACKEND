package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.review.dto.in.ReviewLikeRequestDto;
import org.example.sivillage.review.dto.out.ReviewLikeCountResponseDto;
import org.example.sivillage.review.infrastructure.ReviewLikeRepository;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Transactional
public class ReviewLikeServiceImpl implements ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    public void toggleReviewLike(ReviewLikeRequestDto reviewLikeRequestDto, Long reviewId, String memberUuid) {
        reviewLikeRepository.findByReviewIdAndMemberUuid(reviewId, memberUuid)
                .orElse(reviewLikeRepository.save(ReviewLikeRequestDto.toEntity(reviewLikeRequestDto)));

        // 값이 있다면
        reviewLikeRequestDto.toggleLike(); // 좋아요 해제
        reviewLikeRepository.save(ReviewLikeRequestDto.toEntity(reviewLikeRequestDto));
    }

    public ReviewLikeCountResponseDto getReviewLikeCount(Long reviewId){
       return ReviewLikeCountResponseDto.from(reviewLikeRepository.countByReviewId(reviewId));
    }

}
