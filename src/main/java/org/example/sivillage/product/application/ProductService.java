package org.example.sivillage.product.application;

import org.example.sivillage.product.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.product.dto.out.GetProductBriefInfoResponseDto;
import org.example.sivillage.product.vo.in.CreateProductImageListRequestDto;

import java.util.List;

public interface ProductService {



    /** 1. 상품 등록
     * @param createProductRequestDto 상품 등록 요청 DTO
     */
    void addProduct(CreateProductRequestDto createProductRequestDto);

    /** 2. 상품 옵션 등록
     * @param createProductOptionRequestDto 상품 옵션 등록 요청 DTO
     */
    void addProductOption(CreateProductOptionRequestDto createProductOptionRequestDto);

    /** 3. 상품 이미지 등록
     * @param createProductImageListRequestDto 상품 이미지 등록 요청 DTO
     */
    void addProductImageList(List<CreateProductImageListRequestDto> createProductImageListRequestDto);

    /** 4. 상품 간략 정보 조회
     * @param productCode 상품 코드
     * @return GetProductBriefInfoResponseDto
     */
    GetProductBriefInfoResponseDto getProductBriefInfo(String productCode);
}
