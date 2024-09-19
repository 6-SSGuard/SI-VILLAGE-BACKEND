package org.example.sivillage.question.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQuestionAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String answerContent;

    @Column(nullable = false)
    private String memberUuid;

    @OneToOne
    @JoinColumn(name = "product_question_id", referencedColumnName = "id", nullable = false)
    private ProductQuestion productQuestion;

    @Builder
    public ProductQuestionAnswer(Long id, String answerContent, String memberUuid, ProductQuestion productQuestion) {
        this.id = id;
        this.answerContent = answerContent;
        this.memberUuid = memberUuid;
        this.productQuestion = productQuestion;
    }

}


