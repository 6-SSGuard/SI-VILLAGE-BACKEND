package org.example.sivillage.product.application;

import org.example.sivillage.product.dto.out.GetLikeCountResponseDto;

public interface ProductLikeService {

    /**
     * ProductLikeService interface
     * 1. getProductLike 상품 좋아요 여부 조회
     * 2. toggleProductLike 상품 좋아요 토글
     */

    /**
     * 1. getProductLike 상품 좋아요 여부 조회
     * @param productUuid 상품 UUID
     * @return GetLikeCountResponseDto
     */
    GetLikeCountResponseDto getLikeCount(String productUuid);

    /**
     * 2. toggleProductLike 상품 좋아요 토글
     * @param productCode 상품 UUID
     * @param memberUuid 회원 UUID
     */
    void toggleProductLike(String productCode, String memberUuid);


    public void syncProductLikeWithDB();
}
