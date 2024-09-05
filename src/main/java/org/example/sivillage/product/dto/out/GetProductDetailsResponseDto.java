package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.domain.ProductOption;

@Getter
@Builder
public class GetProductDetailsResponseDto {
    private String productUuid;
    private String productName;
    private Integer price;
    private String brandEngName;
    private String brandKorName;
    private String color;
    private String size;
    private String volume;
    private Integer stock;
    private Integer likesCount;
    private boolean isLiked;

    public static GetProductDetailsResponseDto toDto(Product product, ProductOption productOption, Integer likesCount,
                                                     boolean isLiked) {
        return GetProductDetailsResponseDto.builder()
                .productUuid(product.getProductCode())
                .productName(product.getProductName())
                .price(product.getPrice())
                .color(productOption.getColor().name())
                .size(productOption.getSize().name())
                .stock(productOption.getStock())
                .likesCount(likesCount)
                .isLiked(isLiked)
                .build();
    }
}