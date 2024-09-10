package org.example.sivillage.question.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.question.domain.ProductQuestion;
import org.example.sivillage.question.vo.out.ProductQuestionResponseVo;

import java.time.LocalDateTime;

@Getter
public class ProductQuestionResponseDto {

    private Long productQuestionId;

    private String authorEmail;

    private LocalDateTime productQuestionDate;

    private String questionContent;

    private boolean privateMessage;

    public static ProductQuestionResponseDto from(ProductQuestion productQuestion) {
        return ProductQuestionResponseDto.builder()
                .productQuestionId(productQuestion.getId())
                .authorEmail(productQuestion.getAuthorEmail().substring(0, 4) + "*******")
                .productQuestionDate(productQuestion.getCreatedDate())
                .questionContent(productQuestion.getQuestionContent())
                .privateMessage(productQuestion.isPrivateMessage())
                .build();
    }

    public ProductQuestionResponseVo toResponseVo(){
        return ProductQuestionResponseVo.builder()
                .productQuestionId(this.productQuestionId)
                .authorEmail(this.authorEmail)
                .productQuestionDate(this.productQuestionDate)
                .questionContent(this.questionContent)
                .privateMessage(this.privateMessage)
                .build();
    }

    @Builder
    public ProductQuestionResponseDto(Long productQuestionId, String authorEmail, LocalDateTime productQuestionDate, String questionContent, boolean privateMessage){
        this.productQuestionId = productQuestionId;
        this.authorEmail = authorEmail;
        this.productQuestionDate = productQuestionDate;
        this.questionContent = questionContent;
        this.privateMessage = privateMessage;
    }

}
