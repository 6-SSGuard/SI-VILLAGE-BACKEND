package org.example.sivillage.brand.application;

import org.example.sivillage.brand.dto.out.GetBrandLikeResponseDto;

public interface BrandLikeService {

    /**
     * getBrandLike 브랜드 좋아요 여부 조회
     * @param brandId 브랜드 ID
     * @param memberUuid 회원 UUID
     * return GetBrandLikeResponseDto
     */
    GetBrandLikeResponseDto getBrandLike(Long brandId, String memberUuid);

    /**
     * toggleBrandLike 브랜드 좋아요 토글
     * @param brandId 브랜드 ID
     * @param memberUuid 회원 UUID
     * return void
     */
    void toggleBrandLike(Long brandId, String memberUuid);

}
