package org.example.sivillage.question.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.question.dto.in.ProductQuestionRequestDto;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_question_id")
    private Long productQuestionId;

    @Column(nullable = false)
    private String authorEmail;

    @Column(nullable = false)
    private String questionContent;

    @Column(nullable = false)
    private boolean privateMessage;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String productUuid;

    @Builder
    public ProductQuestion(Long productQuestionId, String authorEmail, String questionContent, boolean privateMessage, String memberUuid, String productUuid){
        this.authorEmail = authorEmail;
        this.questionContent = questionContent;
        this.privateMessage = privateMessage;
        this.memberUuid = memberUuid;
        this.productUuid = productUuid;
    }

    public static ProductQuestion toEntity(ProductQuestionRequestDto dto, String authorEmail, String productUuid, String memberUuid){
        return ProductQuestion.builder()
                .authorEmail(authorEmail)
                .questionContent(dto.getQuestionContent())
                .privateMessage(dto.isPrivateMessage())
                .memberUuid(memberUuid)
                .productUuid(productUuid)
                .build();

    }

}
