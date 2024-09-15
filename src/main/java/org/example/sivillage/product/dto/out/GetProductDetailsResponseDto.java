package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.vo.out.GetProductDetailsResponseVo;

@Getter
@NoArgsConstructor
public class GetProductDetailsResponseDto {
    private String productCode;
    private String productName;
    private Integer price;
    private String brandEngName;
    private Long colorId;
    private String detailContent;

    @Builder
    public GetProductDetailsResponseDto(String productCode, String productName, Integer price, String brandEngName, Long colorId, String detailContent) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.brandEngName = brandEngName;
        this.colorId = colorId;
        this.detailContent = detailContent;
    }

    public static GetProductDetailsResponseDto of(Product product, Brand brand) {
        return GetProductDetailsResponseDto.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .price(product.getPrice())
                .brandEngName(brand.getBrandEngName())
                .colorId(product.getColorId())
                .detailContent(product.getDetailContent())
                .build();
    }

    public GetProductDetailsResponseVo toVo() {
        return GetProductDetailsResponseVo.builder()
                .productCode(productCode)
                .productName(productName)
                .price(price)
                .brandEngName(brandEngName)
                .colorId(colorId)
                .detailContent(detailContent)
                .build();
    }
}