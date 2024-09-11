package org.example.sivillage.brand.application;

import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandIdListResponseDto;

import java.util.List;

public interface BrandService {

    /**
     * BrandService interface
     * 1. addBrand
     * 2. getBrandIdList
     * 3. getBrands
     */


    /**
     * 1. addBrand
     * @param request
     * return void
     */
    void addBrand(AddBrandRequestDto request);

    /**
     * 2. getBrandIdList
     * @param memberUuid
     * return GetBrandIdListResponseDto
     */
    List<GetBrandIdListResponseDto> getBrandIdList(String memberUuid);

    /**
     * 3. getBrands
     * @param memberUuid
     * return GetBrandsListResponseDto
     */
}
