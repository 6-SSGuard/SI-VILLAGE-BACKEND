package org.example.sivillage.product.application;

public interface ProductViewCountService {

    // 상품 조회수 증가
    void incrementViewProduct(String productCode);

    // 상품 조회수 db 저장
    void addProductViewCount();

}
