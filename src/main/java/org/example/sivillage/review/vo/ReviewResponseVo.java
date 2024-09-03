package org.example.sivillage.review.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.review.dto.ReviewResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseVo {

    private Long reviewId;

    private Double score;

    private String authorEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDateTime reviewDate;

    private String purchaseOption; // 구매옵션

    private String memberInformation; // 키,몸무게,평소사이즈

    private String reviewContent;

    private Integer reviewLikeCount;

    private List<String> reviewImages;

    public static ReviewResponseVo toVo(ReviewResponseDto dto){
        return ReviewResponseVo.builder()
                .reviewId(dto.getReviewId())
                .score(dto.getScore())
                .authorEmail(dto.getAuthorEmail()) // 이메일 암호화 해서 dto 에 저장
                .reviewDate(dto.getReviewDate())
                .reviewContent(dto.getReviewContent())
                .reviewLikeCount(dto.getReviewLikeCount())
                .reviewImages(dto.getReviewImages())
                .build();
    }

}
