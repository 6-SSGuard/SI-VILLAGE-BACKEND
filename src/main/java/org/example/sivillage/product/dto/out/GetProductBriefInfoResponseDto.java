package org.example.sivillage.product.dto.out;

import lombok.*;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.vo.out.GetProductBriefInfoResponseVo;

@NoArgsConstructor
@Getter
@Builder
public class GetProductBriefInfoResponseDto {
    private String productCode;
    private String brandEngName;
    private String productName;
    private Integer price;

    @Builder
    public GetProductBriefInfoResponseDto(String productCode, String brandEngName, String productName, Integer price) {
        this.productCode = productCode;
        this.brandEngName = brandEngName;
        this.productName = productName;
        this.price = price;
    }

    public static GetProductBriefInfoResponseDto of(Product product, Brand brand) {
        return GetProductBriefInfoResponseDto.builder()
                .productCode(product.getProductCode())
                .brandEngName(brand.getBrandEngName())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }

    public GetProductBriefInfoResponseVo toVo() {
        return GetProductBriefInfoResponseVo.builder()
                .productCode(productCode)
                .brandEngName(brandEngName)
                .productName(productName)
                .price(price)
                .build();
    }
}