package org.example.sivillage.question.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.question.domain.ProductQuestion;
import org.example.sivillage.question.dto.in.ProductQuestionRequestDto;
import org.example.sivillage.question.dto.out.ProductQuestionResponseDto;
import org.example.sivillage.question.infrastructure.ProductQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductQuestionService {

    //todo: 문의는 수정은 불가능, 삭제 후 재작성만 가능함
    private final ProductQuestionRepository productQuestionRepository;

    public void addProductQuestion(ProductQuestionRequestDto dto, String authorEmail, String productUuid, String memberUuid) {
        productQuestionRepository.save(ProductQuestion.toEntity(dto, authorEmail, productUuid, memberUuid));
    }

    // 상품 문의 목록 조회
    public List<ProductQuestionResponseDto> getProductQuestion(String productUuid) {
        return productQuestionRepository.findByProductUuid(productUuid)
                .stream()
                .map(ProductQuestionResponseDto::toDto).toList();

    }

    // 회원의 상품 문의 목록 조회
    public List<ProductQuestionResponseDto> getMemberProductQuestion(String memberUuid) {
        return productQuestionRepository.findByMemberUuid(memberUuid)
                .stream()
                .map(ProductQuestionResponseDto::toDto).toList();
    }

    public void removeProductQuestion(Long productQuestionId, String memberUuid) {
        productQuestionRepository.findByProductQuestionIdAndMemberUuid(productQuestionId,memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.QUESTION_NOT_FOUND));
        productQuestionRepository.deleteById(productQuestionId);
    }

    }





