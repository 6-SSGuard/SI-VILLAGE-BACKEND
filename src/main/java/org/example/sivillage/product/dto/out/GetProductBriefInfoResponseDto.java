package org.example.sivillage.product.dto.out;

import lombok.*;
import org.example.sivillage.product.domain.Product;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetProductBriefInfoResponseDto {
    private String productCode;
    private String brandEngName;
    private String productName;
    private Integer price;
    private boolean isLiked;

    public static GetProductBriefInfoResponseDto toDto(Product product, boolean isLiked) {
        return GetProductBriefInfoResponseDto.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .price(product.getPrice())
                .isLiked(isLiked)
                .build();
    }
}