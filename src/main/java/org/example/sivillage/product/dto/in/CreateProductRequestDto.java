package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.BrandProduct;
import org.example.sivillage.global.common.UuidGenerator;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;


@NoArgsConstructor
@Getter
public class CreateProductRequestDto {

    private String productName;
    private Integer price;
    private Long brandId;
    private String detailContent;
    private Long colorId;

    @Builder
    public CreateProductRequestDto(String productName, Integer price, Long brandId, String detailContent, Long colorId) {
        this.productName = productName;
        this.price = price;
        this.brandId = brandId;
        this.detailContent = detailContent;
        this.colorId = colorId;
    }

    public static CreateProductRequestDto from(CreateProductRequestVo createProductRequestVo) {
        return CreateProductRequestDto.builder()
                .productName(createProductRequestVo.getProductName())
                .colorId(createProductRequestVo.getColorId())
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
                .colorId(colorId)
                .productName(productName)
                .price(price)
                .detailContent(detailContent)
                .brandId(brandId)
                .build();
    }
}