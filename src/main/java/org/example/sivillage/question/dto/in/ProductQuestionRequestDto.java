package org.example.sivillage.question.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.question.domain.ProductQuestion;

@Getter
public class ProductQuestionRequestDto {

    private String authorEmail;

    private String questionContent;

    private boolean privateMessage;

    public ProductQuestion toEntity(ProductQuestionRequestDto productQuestionRequestDto, String productUuid, String memberUuid) {
        return ProductQuestion.builder()
                .productUuid(productUuid)
                .authorEmail(productQuestionRequestDto.getAuthorEmail())
                .questionContent(productQuestionRequestDto.getQuestionContent())
                .privateMessage(productQuestionRequestDto.isPrivateMessage())
                .memberUuid(memberUuid)
                .build();
    }

    @Builder
    public ProductQuestionRequestDto(String authorEmail, String questionContent, boolean privateMessage) {
        this.authorEmail = authorEmail;
        this.questionContent = questionContent;
        this.privateMessage = privateMessage;
    }

    }

