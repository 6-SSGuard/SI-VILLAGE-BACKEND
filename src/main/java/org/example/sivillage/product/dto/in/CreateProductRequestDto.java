package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Builder
    public CreateProductRequestDto(String productName, Integer price, Long brandId, String detailContent) {
        this.productName = productName;
        this.price = price;
        this.brandId = brandId;
        this.detailContent = detailContent;
    }

    public static CreateProductRequestDto from(CreateProductRequestVo createProductRequestVo) {
        return CreateProductRequestDto.builder()
                .productName(createProductRequestVo.getProductName())
                .price(createProductRequestVo.getPrice())
                .brandId(createProductRequestVo.getBrandId())
                .detailContent(createProductRequestVo.getDetailContent())
                .build();
    }

    public Product toEntity() {
        return Product.builder()
                .productCode(UuidGenerator.generateProductCode())
                .productName(productName)
                .price(price)
                .brandId(brandId)
                .detailContent(detailContent)
                .build();
    }
}