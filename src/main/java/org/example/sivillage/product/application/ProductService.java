package org.example.sivillage.product.application;

import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.product.dto.out.CreateProductResponseDto;
import org.example.sivillage.product.dto.out.GetProductBriefInfoResponseDto;
import org.example.sivillage.product.dto.out.GetProductDetailsResponseDto;

public interface ProductService {

    /**
     * 1. 상품 등록
     * 4. 상품 간략 정보 조회
     * 6. 상품 상세 정보 조회
     */


    /** 1. 상품 등록
     * @param createProductRequestDto 상품 등록 요청 DTO
     */
    CreateProductResponseDto addProduct(CreateProductRequestDto createProductRequestDto);

    /** 4. 상품 간략 정보 조회
     * @param productCode 상품 코드
     * @return GetProductBriefInfoResponseDto
     */
    GetProductBriefInfoResponseDto getProductBriefInfo(String productCode);

    /** 6. 상품 상세 정보 조회
     * @param productCode 상품 코드
     * @return GetProductDetailsResponseDto
     */
    GetProductDetailsResponseDto getProductDetail(String productCode);

}
