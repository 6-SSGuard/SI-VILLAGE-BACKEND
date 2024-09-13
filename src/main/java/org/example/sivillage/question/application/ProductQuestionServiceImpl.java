package org.example.sivillage.question.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.question.dto.in.ProductQuestionRequestDto;
import org.example.sivillage.question.dto.out.ProductQuestionResponseDto;
import org.example.sivillage.question.infrastructure.ProductQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductQuestionServiceImpl {

    private final ProductQuestionRepository productQuestionRepository;

    public void addProductQuestion(ProductQuestionRequestDto productQuestionRequestDto, String productUuid, String memberUuid) {
        productQuestionRepository.save(productQuestionRequestDto.toEntity(productQuestionRequestDto, productUuid, memberUuid));
    }

    // 상품 문의 목록 조회
    public List<ProductQuestionResponseDto> getProductQuestion(String productUuid) {
        return productQuestionRepository.findByProductUuid(productUuid)
                .stream()
                .map(ProductQuestionResponseDto::from).toList();
    }

    // 회원의 상품 문의 목록 조회
    public List<ProductQuestionResponseDto> getMemberProductQuestion(String memberUuid) {
        return productQuestionRepository.findByMemberUuid(memberUuid)
                .stream()
                .map(ProductQuestionResponseDto::from).toList();
    }

    public void removeProductQuestion(Long productQuestionId) {
        productQuestionRepository.findById(productQuestionId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.QUESTION_NOT_FOUND));
        productQuestionRepository.deleteById(productQuestionId);
    }

}





