package org.example.sivillage.review.application;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.review.dto.in.ReviewRequestDto;
import org.example.sivillage.review.dto.out.ReviewResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    List<IdListResponseDto<Long>> getProductReviewIds(String productCode);
    List<IdListResponseDto<Long>> getMemberReviewIds(String memberUuid);
    ReviewResponseDto getReview(Long reviewId);
    Long addReview(ReviewRequestDto reviewRequestDto, String memberUuid, String productUuid);
    void changeReview(ReviewRequestDto reviewRequestDto, Long reviewId);
    void removeReview(Long reviewId);
    void addReviewFromCsv(MultipartFile file);
    List<IdListResponseDto<Long>> getSortReviewsByCreatedAt(Long cursor, int pageSize);
}
