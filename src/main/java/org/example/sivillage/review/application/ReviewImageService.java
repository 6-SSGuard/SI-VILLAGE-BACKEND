package org.example.sivillage.review.application;

import org.example.sivillage.review.dto.in.ReviewImageRequestDto;
import org.example.sivillage.review.dto.out.ReviewImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewImageService {
    public void addReviewImage(List<ReviewImageRequestDto> reviewImageRequestDto, Long reviewId);
    List<ReviewImageResponseDto> getReviewImage(Long reviewId);
    void removeReviewImage(Long reviewImageId);
    public void addReviewImageFromCsv(MultipartFile file);
}
