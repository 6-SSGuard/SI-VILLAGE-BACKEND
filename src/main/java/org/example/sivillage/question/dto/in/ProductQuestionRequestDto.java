package org.example.sivillage.question.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.question.domain.ProductQuestion;

@Getter
public class ProductQuestionRequestDto {

    private String questionTitle;

    private String questionContent;

    private boolean privateMessage;

    public ProductQuestion toEntity(ProductQuestionRequestDto productQuestionRequestDto, String productCode, String memberUuid) {
        return ProductQuestion.builder()
                .productCode(productCode)
                .questionTitle(productQuestionRequestDto.getQuestionTitle())
                .questionContent(productQuestionRequestDto.getQuestionContent())
                .privateMessage(productQuestionRequestDto.isPrivateMessage())
                .memberUuid(memberUuid)
                .build();
    }

    @Builder
    public ProductQuestionRequestDto(String questionTitle, String questionContent, boolean privateMessage) {
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.privateMessage = privateMessage;
    }

    }

