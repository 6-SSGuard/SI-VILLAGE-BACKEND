package org.example.sivillage.question.application;
import org.example.sivillage.question.dto.in.ProductQuestionAnswerRequestDto;
import org.example.sivillage.question.dto.out.ProductQuestionAnswerResponseDto;

import java.util.List;

public interface ProductQuestionAnswerService {
    void addProductQuestionAnswer(ProductQuestionAnswerRequestDto productQuestionAnswerRequestDto, Long productQuestionId, String memberUuid);

    ProductQuestionAnswerResponseDto getProductQuestionAnswer(Long productQuestionId); //상품 문의 id 로 답변 내용 가져오기

    List<ProductQuestionAnswerResponseDto> getVendorProductQuestionAnswer(String memberUuid); // 자신이 작성한 상품 문의 내용 리스트 보기

    void changeProductQuestionAnswer(ProductQuestionAnswerRequestDto productQuestionAnswerRequestDto, Long productQuestionAnswerId);
}
