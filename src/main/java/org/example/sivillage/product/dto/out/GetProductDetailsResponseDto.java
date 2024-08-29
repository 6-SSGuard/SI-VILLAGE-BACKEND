package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.domain.ProductOption;

import java.util.List;

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
    private String capacity;
    private Integer stock;
    private Integer likesCount;
    private boolean isLiked;
    private List<String> productImageUrls;

    public static GetProductDetailsResponseDto toDto(Product product, ProductOption productOption, Integer likesCount,
                                                     boolean isLiked, List<String> productImageUrls) {
        return GetProductDetailsResponseDto.builder()
                .productUuid(product.getProductUuid())
                .productName(product.getProductName())
                .price(product.getPrice())
                .brandEngName(product.getBrand().getBrandEngName())
                .brandKorName(product.getBrand().getBrandKorName())
                .color(productOption.getColor().name())
                .size(productOption.getSize().name())
                .capacity(productOption.getCapacity())
                .stock(productOption.getStock())
                .likesCount(likesCount)
                .isLiked(isLiked)
                .productImageUrls(productImageUrls)
                .build();
    }
}