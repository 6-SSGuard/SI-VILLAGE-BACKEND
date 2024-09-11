package org.example.sivillage.brand.application;

import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandsListResponseDto;

public interface BrandService {

    /**
     * BrandService interface
     * 1. addBrand
     * 2. getBrands
     */


    /**
     * 1. addBrand
     * @param request
     * return void
     */
    void addBrand(AddBrandRequestDto request);

    /**
     * 2. getBrands
     * @param memberUuid
     * return GetBrandsListResponseDto
     */
    GetBrandsListResponseDto getBrands(String memberUuid);
}
