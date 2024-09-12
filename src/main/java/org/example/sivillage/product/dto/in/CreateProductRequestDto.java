package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.domain.BrandProduct;
import org.example.sivillage.global.common.UuidGenerator;
import org.example.sivillage.product.domain.Color;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;

@NoArgsConstructor
@Getter
public class CreateProductRequestDto {

    private String productName;
    private Integer price;
    private Long brandId;
    private String detailContent;
    private Color color;

    @Builder
    public CreateProductRequestDto(String productName, Integer price, Long brandId, String detailContent, Color color, Long brandId) {
        this.productName = productName;
        this.price = price;
        this.brandId = brandId;
        this.detailContent = detailContent;
        this.color = color;
    }

    public static CreateProductRequestDto from(CreateProductRequestVo createProductRequestVo) {
        return CreateProductRequestDto.builder()
                .productName(createProductRequestVo.getProductName())
                .color(createProductRequestVo.getColor())
                .price(createProductRequestVo.getPrice())
                .brandId(createProductRequestVo.getBrandId())
                .detailContent(createProductRequestVo.getDetailContent())
                .build();
    }

    public BrandProduct toEntity(Long brandId, String productCode) {
        return BrandProduct.builder()
                .brandId(brandId)
                .productCode(productCode)
                .build();
    }

    public Product toEntity() {
        return Product.builder()
                .productCode(UuidGenerator.generateProductCode())
                .color(color)
                .productName(productName)
                .price(price)
                .detailContent(detailContent)
                .brandId(brandId)
                .build();
    }
}