package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.ProductViewCount;

@Getter
@NoArgsConstructor
public class ProductViewCountRequestDto {
    private String productCode;
    private Long viewCount;

    public static ProductViewCount toEntity(String productCode, Long viewCount) {
        return ProductViewCount.builder()
                .productCode(productCode)
                .viewCount(viewCount)
                .build();
    }

    public static ProductViewCount updateToEntity(ProductViewCount productViewCount, Long viewCount){
        return ProductViewCount.builder()
                .id(productViewCount.getId())
                .productCode(productViewCount.getProductCode())
                .viewCount(viewCount)
                .build();
    }

    @Builder
    public ProductViewCountRequestDto(String productCode, Long viewCount) {
        this.productCode = productCode;
        this.viewCount = viewCount;
    }
}
