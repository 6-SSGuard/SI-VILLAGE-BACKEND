package org.example.sivillage.review.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.review.dto.ReviewRequestDto;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private String authorEmail;

    @Column(nullable = true)
    private String purchaseOption; // 구매옵션

    @Column(nullable = true)
    private String memberInformation; // 키,몸무게,평소사이즈,신발사이즈,뷰티정보 등등

    @Column(nullable = false, length = 2000)
    private String reviewContent;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String productUuid;

    @Column(nullable = false)
    private Integer reviewLikeCount;


    @Builder
    public Review(Long reviewId, Double score, String authorEmail, String purchaseOption, String memberInformation, String reviewContent, Integer reviewLikeCount, String memberUuid,String productUuid) {
        this.reviewId = reviewId;
        this.score = score;
        this.authorEmail = authorEmail;
        this.purchaseOption = purchaseOption;
        this.memberInformation = memberInformation;
        this.reviewContent = reviewContent;
        this.reviewLikeCount = reviewLikeCount;
        this.memberUuid = memberUuid;
        this.productUuid = productUuid;
    }

    public static Review toEntity (ReviewRequestDto dto, String authorEmail, String memberUuid, String productUuid) {
        return Review.builder()
                .score(dto.getScore())
                .authorEmail(authorEmail)
              //  .purchaseOption(purchaseOption)
                .reviewContent(dto.getReviewContent())
                .reviewLikeCount(0)
                .memberUuid(memberUuid)
                .productUuid(productUuid)
                .build();
    }


    public void toEntityMemberInfo(String memberInformation){

        this.memberInformation = memberInformation;
    }

    public void change(ReviewRequestDto dto){
        this.score = dto.getScore();
        this.reviewContent = dto.getReviewContent();
    }

    public  void incrementLikeCount() {
        this.reviewLikeCount++;
    }

    // 좋아요 카운트 감소
    public void decrementLikeCount() {
        if (this.reviewLikeCount > 0) {
            this.reviewLikeCount--;
        }
    }
}
