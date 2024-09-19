package org.example.sivillage.question.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.question.domain.ProductQuestionAnswer;
import org.example.sivillage.question.vo.out.ProductQuestionAnswerResponseVo;


@Getter
public class ProductQuestionAnswerResponseDto {

    private Long questionAnswerId;

    private String questionAnswerContent;


    public static ProductQuestionAnswerResponseDto from(ProductQuestionAnswer productQuestionAnswer) {
        return ProductQuestionAnswerResponseDto.builder()
                .questionAnswerId(productQuestionAnswer.getId())
                .questionAnswerContent(productQuestionAnswer.getAnswerContent())
                .build();
    }

    public ProductQuestionAnswerResponseVo toResponseVo(){
        return ProductQuestionAnswerResponseVo.builder()
                .questionAnswerId(this.questionAnswerId)
                .questionAnswerContent(this.questionAnswerContent)
                .build();
    }

    @Builder
    public ProductQuestionAnswerResponseDto(Long questionAnswerId, String questionAnswerContent){
        this.questionAnswerId = questionAnswerId;
        this.questionAnswerContent = questionAnswerContent;
    }
}
