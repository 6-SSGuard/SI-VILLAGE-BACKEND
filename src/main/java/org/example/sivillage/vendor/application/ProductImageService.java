package org.example.sivillage.vendor.application;

import org.example.sivillage.vendor.dto.out.GetProductImageUrlListResponseDto;
import org.example.sivillage.vendor.dto.out.GetProductThumbnailUrlResponseDto;
import org.example.sivillage.vendor.dto.in.CreateProductImageListRequestDto;

import java.util.List;

public interface ProductImageService {

    /** 3. 상품 이미지 등록
     * @param createProductImageListRequestDto 상품 이미지 등록 요청 DTO
     */
    void addProductImageList(List<CreateProductImageListRequestDto> createProductImageListRequestDto);

    void updateProductImageList(List<CreateProductImageListRequestDto> createProductImageListRequestDto);

    /** 7. 상품 썸네일 URL 조회
     * @param productCode 상품 코드
     * @return GetProductThumbnailUrlResponseDto
     */
    GetProductThumbnailUrlResponseDto getProductThumbnailUrl(String productCode);

    /** 8. 상품 이미지 URL 리스트 조회
     * @param productCode 상품 코드
     * @return GetProductImageUrlListResponseDto
     */
    List<GetProductImageUrlListResponseDto> getProductImageUrlList(String productCode);
}
