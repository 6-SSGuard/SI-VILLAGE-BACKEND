package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.in.ReviewRequestDto;
import org.example.sivillage.review.dto.out.ReviewResponseDto;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    //todo: 리뷰 id 리스트로 리뷰 객체 가져오는 방식이 맞는지 물어보기
    //todo: 캐싱처리를 생각해서 좋아요, 이미지, 내용 이렇게 3분할로 쪼개서 api 만들 예정 나머지 뷰티정보,주문내역은 등록 시 함께 넣는 걸로 생각중
    //todo :리뷰 id리스트로 각 리뷰 1개당 상세 정보를 불러오는 행위를 리뷰가 100개면 총 300번의 호출이 오는 것인데 이게 맞는가..?  구현하는건 상관 없는데 이렇게 했다가
    //todo : 잘못되었다고 하실까봐 코드를 쓰지를 못하겠네..

    public Long addReview(ReviewRequestDto reviewRequestDto, String memberUuid, String productUuid) {
        Review review = reviewRepository.save(reviewRequestDto.toEntity(reviewRequestDto,memberUuid,productUuid));
        return review.getId();
    }

    public List<ReviewResponseDto> getProductReview(Long id) {
        return reviewRepository.findById(id);
                .stream()
                .map(ReviewResponseDto::from).toList();
    }

    public void changeReview(ReviewRequestDto reviewRequestDto, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));
        reviewRepository.save(reviewRequestDto.updateToEntity(reviewRequestDto,review));
    }

    public void removeReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));
        reviewRepository.delete(review);
    }
}










