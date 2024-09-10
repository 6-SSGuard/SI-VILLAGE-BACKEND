package org.example.sivillage.question.application;

import org.example.sivillage.question.dto.in.ProductQuestionRequestDto;

public interface ProductQuestionService {
    void addProductQuestion(ProductQuestionRequestDto productQuestionRequestDto, String productUuid, String memberUuid);

}
