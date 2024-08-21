package org.example.sivillage.domain.product.vo;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.domain.product.domain.Product;
import org.example.sivillage.domain.product.domain.ProductOption;

@Getter
@Builder
public class GetProductDetailsResponse {
    private Long productId;
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
                .productId(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .brandName(product.getBrand().getName())
                .color(productOption.getColor().name())
                .size(productOption.getSize().name())
                .capacity(productOption.getCapacity())
                .stock(productOption.getStock())
                .likesCount(likesCount)
                .build();
    }
}