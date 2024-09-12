package org.example.sivillage.product.application;

import org.example.sivillage.product.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.product.dto.out.GetProductBriefInfoResponseDto;
import org.example.sivillage.product.dto.out.GetProductDetailsResponseDto;
import org.example.sivillage.product.dto.out.GetProductOptionListResponseDto;
import org.example.sivillage.product.vo.in.CreateProductImageListRequestDto;

import java.util.List;

public interface ProductService {

    /**
     * 1. 상품 등록
     * 2. 상품 옵션 등록
     * 3. 상품 이미지 등록
     * 4. 상품 간략 정보 조회
     * 5. 상품 옵션 정보 조회
     */


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

    /** 5. 상품 옵션 리스트 정보 조회
     * @param productCode 상품 코드
     * @return GetProductOptionListResponseDto
     */
    List<GetProductOptionListResponseDto> getProductOptionList(String productCode);

    /** 6. 상품 상세 정보 조회
     * @param productCode 상품 코드
     * @param memberUuid 회원 UUID
     * @return GetProductDetailsResponseDto
     */
    GetProductDetailsResponseDto getProductDetail(String productCode, String memberUuid);
}
