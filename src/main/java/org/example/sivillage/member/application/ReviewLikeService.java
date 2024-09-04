package org.example.sivillage.member.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.member.domain.ReviewLike;
import org.example.sivillage.review.infrastructure.ReviewLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    // 회원이 리뷰 좋아요 눌렀을 때
    public Integer addReviewLike(Long reviewId, String memberUuid){
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByMemberUuid(memberUuid);
        if(reviewLike.isEmpty()) reviewLikeRepository.save()
    }

}
