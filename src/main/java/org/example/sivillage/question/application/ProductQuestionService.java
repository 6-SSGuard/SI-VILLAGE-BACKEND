package org.example.sivillage.question.application;

import org.example.sivillage.question.dto.in.ProductQuestionRequestDto;
import org.example.sivillage.question.dto.out.ProductQuestionResponseDto;

import java.util.List;

public interface ProductQuestionService {
    void addProductQuestion(ProductQuestionRequestDto productQuestionRequestDto, String productUuid, String memberUuid);
    List<ProductQuestionResponseDto> getProductQuestion(String productUuid);
    List<ProductQuestionResponseDto> getMemberProductQuestion(String memberUuid);
    void removeProductQuestion(Long productQuestionId, String memberUuid);
}
