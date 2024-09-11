package org.example.sivillage.brand.application;

import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandIdListResponseDto;
import org.example.sivillage.brand.dto.out.GetBrandLikeResponseDto;
import org.example.sivillage.brand.dto.out.GetBrandNameResponseDto;

import java.util.List;

public interface BrandService {

    /**
     * BrandService interface
     * 1. addBrand 브랜드 생성
     * 2. getBrandIdList 브랜드 ID 목록 조회
     * 3. getBrandName 브랜드 정보 조회
     * 4. getBrandLike 브랜드 좋아요 여부 조회
     */


    /**
     * 1. addBrand 브랜드 생성
     * @param request
     * return void
     */
    void addBrand(AddBrandRequestDto request);

    /**
     * 2. getBrandIdList 브랜드 ID 목록 조회
     * @param memberUuid 회원 UUID
     * return GetBrandIdListResponseDto
     */
    List<GetBrandIdListResponseDto> getBrandIdList(String memberUuid);

    /**
     * 3. getBrandInfo 브랜드 정보 조회
     * @param brandId 브랜드 ID
     * return GetBrandNameResponseDto
     */
    GetBrandNameResponseDto getBrandName(Long brandId);

    /**
     * 4. getBrandLike 브랜드 좋아요 여부 조회
     * @param brandId 브랜드 ID
     * @param memberUuid 회원 UUID
     * return GetBrandLikeResponseDto
     */
    GetBrandLikeResponseDto getBrandLike(Long brandId, String memberUuid);
}
