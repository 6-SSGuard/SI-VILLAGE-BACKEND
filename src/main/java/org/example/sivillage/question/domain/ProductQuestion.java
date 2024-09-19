package org.example.sivillage.question.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String questionTitle;

    @Column(nullable = false)
    private String questionContent;

    @Column(nullable = false)
    private boolean privateMessage;

    @Column(nullable = false)
    private boolean answerStatus = false;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String productCode;

    @OneToOne(mappedBy = "productQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductQuestionAnswer productQuestionAnswer;


    @Builder
    public ProductQuestion(Long id, String questionTitle, String questionContent, boolean privateMessage, boolean answerStatus, String memberUuid, String productCode) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.privateMessage = privateMessage;
        this.answerStatus = answerStatus;
        this.memberUuid = memberUuid;
        this.productCode = productCode;
    }

}


