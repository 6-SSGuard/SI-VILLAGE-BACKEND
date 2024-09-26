package org.example.sivillage.review.application;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.sivillage.beautyInfo.domain.BeautyInfo;
import org.example.sivillage.beautyInfo.infrastructure.BeautyInfoRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CategoryPathService categoryPathService;
    private final BeautyInfoRepository beautyInfoRepository;
    private final SizeInfoRepository sizeInfoRepository;
    private final MemberRepository memberRepository;
    private final JPAQueryFactory jpaQueryFactory;

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
        return ReviewResponseDto.from(reviewRepository.findById(reviewId).orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND)));
    }

    // 리뷰 등록
    public Long addReview(ReviewRequestDto reviewRequestDto, String memberUuid, String productCode) {

        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid).orElse(new BeautyInfo());
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid).orElse(new SizeInfo());
        CategoryType categoryType = CategoryType.fromCategoryPath(categoryPathService.getCategoryPath(productCode));
        categoryType.getInfo(beautyInfo, sizeInfo);

        Review review = reviewRepository.save(reviewRequestDto.toEntity(reviewRequestDto,
                memberRepository.findEmailByMemberUuid(memberUuid),
                categoryType.getInfo(beautyInfo, sizeInfo),
                memberUuid,
                productCode));

        return review.getId();
    }

    public void changeReview(ReviewRequestDto reviewRequestDto, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REVIEW_NOT_FOUND));
        reviewRepository.save(reviewRequestDto.updateToEntity(reviewRequestDto, review));
    }

    public void removeReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // csv 파일로 리뷰 데이터 추가
    public void addReviewFromCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<CSVRecord> records = csvParser.getRecords();
            Random random = new Random();

            for (CSVRecord record : records) {
                // 1~100 사이의 랜덤 숫자를 생성
                int randomReviewCount = random.nextInt(100) + 1;

                Double score = Double.valueOf(record.get("score")); // CSV 컬럼 이름 사용
                String reviewContent = record.get("reviewContent");
                String authorEmail = record.get("authorEmail");
                String memberInformation = record.get("memberInformation");
                String memberUuid = record.get("memberUuid");
                String productCode = record.get("productCode");

                // 랜덤한 수만큼 리뷰 생성 및 저장
                for (int i = 0; i < randomReviewCount; i++) {
                    Review review = Review.builder()
                            .score(score)
                            .reviewContent(reviewContent)
                            .authorEmail(authorEmail)
                            .memberInformation(memberInformation)
                            .memberUuid(memberUuid)
                            .productCode(productCode)
                            .build();

                    reviewRepository.save(review);
                }
            }
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);

        }
    }

}








