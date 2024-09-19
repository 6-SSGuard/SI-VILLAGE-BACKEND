package org.example.sivillage.vendor.application;

import org.example.sivillage.vendor.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductOptionListResponseDto;

import java.util.List;

public interface ProductOptionService {

    /** 2. 상품 옵션 등록
     * @param createProductOptionRequestDto 상품 옵션 등록 요청 DTO
     */
    void addProductOption(CreateProductOptionRequestDto createProductOptionRequestDto);

    /** 5. 상품 옵션 리스트 정보 조회
     * @param productCode 상품 코드
     * @return GetProductOptionListResponseDto
     */
    List<GetProductOptionListResponseDto> getProductOptionList(String productCode);
}
