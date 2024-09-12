package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;
import org.example.sivillage.BeautyInfo.infrastructure.BeautyInfoRepository;
import org.example.sivillage.global.common.CategoryPathService;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.example.sivillage.review.domain.CategoryType;
import org.example.sivillage.review.domain.Review;
import org.example.sivillage.review.dto.in.ReviewRequestDto;
import org.example.sivillage.review.dto.out.ReviewResponseDto;
import org.example.sivillage.review.infrastructure.ReviewRepository;
import org.example.sivillage.sizeinfo.domain.SizeInfo;
import org.example.sivillage.sizeinfo.infrastructure.SizeInfoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CategoryPathService categoryPathService;
    private final BeautyInfoRepository beautyInfoRepository;
    private final SizeInfoRepository sizeInfoRepository;
    private final MemberRepository memberRepository;

    // 상품에 대한 리뷰 id 리스트
    public List<IdListResponseDto<Long>> getProductReviewIds(String productCode) {

        return reviewRepository.findIdByProductCode(productCode)
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    // 회원에 대한 리뷰 id 리스트
    public List<IdListResponseDto<Long>> getMemberReviewIds(String memberUuid) {
        return reviewRepository.findIdByMemberUuid(memberUuid)
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    //단일 리뷰 조회
    public ReviewResponseDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        String email = "naver";
        String memberInfo = "info";
        // 리뷰가 반드시 존재한다고 가정
        return ReviewResponseDto.from(review,email,memberInfo);
    }

    // 리뷰 등록
    public Long addReview(ReviewRequestDto reviewRequestDto, String memberUuid, String productUuid) {

        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid).orElse(new BeautyInfo());
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid).orElse(new SizeInfo());
        CategoryType categoryType = CategoryType.fromCategoryPath(categoryPathService.getCategoryPath(productUuid));
        categoryType.getInfo(beautyInfo,sizeInfo);

        Review review = reviewRepository.save(reviewRequestDto.toEntity(reviewRequestDto,categoryType.getInfo(beautyInfo,sizeInfo),memberRepository.findEmailByMemberUuid(memberUuid), memberUuid,productUuid));
        return review.getId();
    }

    public void changeReview(ReviewRequestDto reviewRequestDto, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));
        reviewRepository.save(reviewRequestDto.updateToEntity(reviewRequestDto, review));
    }

    public void removeReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));
        reviewRepository.delete(review);
    }
}










