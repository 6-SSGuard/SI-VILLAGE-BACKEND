package org.example.sivillage.question.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.question.domain.ProductQuestion;

@Getter
public class ProductQuestionRequestDto {

    private String questionContent;

    private boolean privateMessage;

    public ProductQuestion toEntity(ProductQuestionRequestDto productQuestionRequestDto, String productUuid, String memberUuid) {
        return ProductQuestion.builder()
                .productUuid(productUuid)
                .questionContent(productQuestionRequestDto.getQuestionContent())
                .privateMessage(productQuestionRequestDto.isPrivateMessage())
                .memberUuid(memberUuid)
                .build();
    }

    @Builder
    public ProductQuestionRequestDto(String questionContent, boolean privateMessage) {
        this.questionContent = questionContent;
        this.privateMessage = privateMessage;
    }

    }

