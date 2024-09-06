package org.example.sivillage.question.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductQuestionAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_question_answer_id;

    @Column(nullable = false)
    private boolean isAnswered;

    @Column(nullable = false)
    private String answerContent;

    @Column(nullable = false)
    private String memberUuid;

    @OneToOne
    @JoinColumn(name = "product_question_id", nullable = false)
    private ProductQuestion productQuestion;

}
