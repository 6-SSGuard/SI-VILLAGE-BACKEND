package org.example.sivillage.product.vo;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.domain.ProductOption;

@Getter
@Builder
public class GetProductDetailsResponse {
    private String productCode;
    private String productName;
    private Integer price;
    private String brandName;
    private String color;
    private String size;
    private String capacity;
    private Integer stock;
    private Long likesCount;

    public static GetProductDetailsResponse toDto(Product product, ProductOption productOption, Long likesCount) {
        return GetProductDetailsResponse.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .price(product.getPrice())
                .brandName(product.getBrand().getBrandName())
                .color(productOption.getColor().name())
                .size(productOption.getSize().name())
                .capacity(productOption.getCapacity())
                .stock(productOption.getStock())
                .likesCount(likesCount)
                .build();
    }
}