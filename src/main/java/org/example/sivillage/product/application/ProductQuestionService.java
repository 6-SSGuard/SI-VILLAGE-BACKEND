package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.product.dto.in.ProductQuestionRequestDto;
import org.example.sivillage.product.infrastructure.ProductQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductQuestionService {

    private final ProductQuestionRepository productQuestionRepository;

    public void addProductQuestion(ProductQuestionRequestDto dto, String productUuid, String memberUuid) {




}



}
