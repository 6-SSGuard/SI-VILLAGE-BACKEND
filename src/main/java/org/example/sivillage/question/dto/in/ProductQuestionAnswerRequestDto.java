package org.example.sivillage.question.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.question.domain.ProductQuestion;
import org.example.sivillage.question.domain.ProductQuestionAnswer;

@Getter
public class ProductQuestionAnswerRequestDto {

    private String questionAnswerContent;

    private String memberUuid;

    public ProductQuestionAnswer toEntity(ProductQuestionAnswerRequestDto productQuestionAnswerRequestDto, String memberUuid, ProductQuestion productQuestion) {
        return ProductQuestionAnswer.builder()
                .answerContent(productQuestionAnswerRequestDto.getQuestionAnswerContent())
                .memberUuid(memberUuid)
                .productQuestion(productQuestion)
                .build();
    }

    public ProductQuestion changeAnswerStatus(ProductQuestion productQuestion, boolean answerStatus) {
        return ProductQuestion.builder()
                .id(productQuestion.getId())
                .questionTitle(productQuestion.getQuestionTitle())
                .questionContent(productQuestion.getQuestionContent())
                .privateMessage(productQuestion.isPrivateMessage())
                .memberUuid(productQuestion.getMemberUuid())
                .productCode(productQuestion.getProductCode())
                .answerStatus(answerStatus)
                .build();
    }

    @Builder
    public ProductQuestionAnswerRequestDto(String questionAnswerContent, String memberUuid) {
        this.questionAnswerContent = questionAnswerContent;
        this.memberUuid = memberUuid;
    }

}
