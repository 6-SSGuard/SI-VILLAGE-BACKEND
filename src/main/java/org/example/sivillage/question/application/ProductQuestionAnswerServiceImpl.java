package org.example.sivillage.question.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.question.domain.ProductQuestion;
import org.example.sivillage.question.domain.ProductQuestionAnswer;
import org.example.sivillage.question.dto.in.ProductQuestionAnswerRequestDto;
import org.example.sivillage.question.dto.out.ProductQuestionAnswerResponseDto;
import org.example.sivillage.question.infrastructure.ProductQuestionAnswerRepository;
import org.example.sivillage.question.infrastructure.ProductQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ProductQuestionAnswerServiceImpl implements ProductQuestionAnswerService{

  private final ProductQuestionAnswerRepository productQuestionAnswerRepository;
  private final ProductQuestionRepository productQuestionRepository;
    public void addProductQuestionAnswer(ProductQuestionAnswerRequestDto productQuestionAnswerRequestDto, Long productQuestionId, String memberUuid) {
        ProductQuestion productQuestion = productQuestionRepository.findById(productQuestionId).get();
        productQuestionAnswerRepository.save(productQuestionAnswerRequestDto.toEntity(productQuestionAnswerRequestDto,memberUuid,productQuestion));
        productQuestionRepository.save(productQuestionAnswerRequestDto.changeAnswerStatus(productQuestion,true)); // 답변여부 true 로 변경
    }

    public ProductQuestionAnswerResponseDto getProductQuestionAnswer(Long productQuestionId) {
       ProductQuestion productQuestion = productQuestionRepository.findById(productQuestionId).orElseThrow(()->new BaseException(BaseResponseStatus.QUESTION_NOT_FOUND));
       ProductQuestionAnswer productQuestionAnswer = productQuestionAnswerRepository.findByProductQuestion(productQuestion).get();
        return ProductQuestionAnswerResponseDto.from(productQuestionAnswer);
    }

    public List<ProductQuestionAnswerResponseDto> getVendorProductQuestionAnswer(String memberUuid) {
        return productQuestionAnswerRepository.findByMemberUuid(memberUuid)
                .stream()
               .map(ProductQuestionAnswerResponseDto::from).toList();
    }

    public void changeProductQuestionAnswer(ProductQuestionAnswerRequestDto productQuestionAnswerRequestDto, Long productQuestionAnswerId){
        ProductQuestionAnswer productQuestionAnswer = productQuestionAnswerRepository.findById(productQuestionAnswerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.QUESTION_NOT_FOUND));
        productQuestionAnswerRepository.save(productQuestionAnswerRequestDto.updateToEntity(productQuestionAnswerRequestDto,productQuestionAnswer)); // ��변여부 false 로 변경

    }

}
