package org.example.sivillage.product.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.example.sivillage.global.common.BaseEntity;


@Entity
@Getter
public class ProductQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_question_id")
    private Long productQuestionId;

    @Column(nullable = false)
    private String questionContent;

    @Column(nullable = false)
    private boolean isPrivate;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String productUuid;

    @OneToOne
    @JoinColumn(name = "product_answer_id", nullable = false)
    private ProductQuestionAnswer productQuestionAnswer;



}
