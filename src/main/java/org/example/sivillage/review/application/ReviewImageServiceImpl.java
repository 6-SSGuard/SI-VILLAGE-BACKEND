package org.example.sivillage.review.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.review.domain.ReviewImage;
import org.example.sivillage.review.dto.in.ReviewImageRequestDto;
import org.example.sivillage.review.dto.out.ReviewImageResponseDto;
import org.example.sivillage.review.infrastructure.ReviewImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

    //csv 파일로 리뷰 이미지 추가

    public void addReviewImageFromCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<CSVRecord> records = csvParser.getRecords();
            for (CSVRecord record : records) {
                Long id = Long.valueOf(record.get("id")); // CSV 컬럼 이름 사용
                String reviewImageUrl = record.get("reviewImageUrl");
                Long reviewId = Long.valueOf(record.get("reviewId"));

                // 리뷰이미지 엔티티 생성 및 저장
                ReviewImage reviewImage = ReviewImage.builder()
                        .id(id)
                        .reviewImageUrl(reviewImageUrl)
                        .reviewId(reviewId)
                        .build();

             reviewImageRepository.save(reviewImage);
            }
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
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
