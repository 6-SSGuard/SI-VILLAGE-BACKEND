package org.example.sivillage.product.application;

import org.example.sivillage.product.dto.in.CreateProductRequestDto;

public interface ProductService {



    /** 1. 상품 등록
     * @param createProductRequestDto 상품 등록 요청 DTO
     */
    void addProduct(CreateProductRequestDto createProductRequestDto);
}
