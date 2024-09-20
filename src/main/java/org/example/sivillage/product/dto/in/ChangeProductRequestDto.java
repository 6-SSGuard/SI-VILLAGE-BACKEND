package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.product.domain.BrandProduct;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.vo.in.ChangeProductRequestVo;

@Getter
@NoArgsConstructor
public class ChangeProductRequestDto {

    private String productCode;
    private String productName;
    private Integer price;
    private Long colorId;
    private String detailContent;
    private Long brandId;

    @Builder
    public ChangeProductRequestDto(String productCode, String productName, Integer price, Long colorId, String detailContent, Long brandId) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.colorId = colorId;
        this.detailContent = detailContent;
        this.brandId = brandId;
    }

    public static ChangeProductRequestDto from(ChangeProductRequestVo changeProductRequestVo) {
        return ChangeProductRequestDto.builder()
                .productCode(changeProductRequestVo.getProductCode())
                .productName(changeProductRequestVo.getProductName())
                .price(changeProductRequestVo.getPrice())
                .colorId(changeProductRequestVo.getColorId())
                .detailContent(changeProductRequestVo.getDetailContent())
                .brandId(changeProductRequestVo.getBrandId())
                .build();
    }

    public Product changeProduct(Long id) {
        return new Product(
                id,
                productCode,
                productName,
                price,
                colorId,
                detailContent,
                brandId
        );
    }

    public BrandProduct changeBrandProduct(Brand brand, String productCode) {
        return new BrandProduct(
                brand.getId(),
                brandId,
                productCode
        );
    }
}
