package org.example.sivillage.review.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false, length = 2000)
    private String reviewContent;

    @Column(nullable = false)
    private String authorEmail;

    @Column(nullable = false)
    private String memberInformation;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String productCode;

    @Builder
    public Review(Long id, Double score, String reviewContent,String authorEmail, String memberInformation, String memberUuid,String productCode) {
        this.id = id;
        this.score = score;
        this.reviewContent = reviewContent;
        this.authorEmail = authorEmail;
        this.memberInformation = memberInformation;
        this.memberUuid = memberUuid;
        this.productCode = productCode;
    }

}
