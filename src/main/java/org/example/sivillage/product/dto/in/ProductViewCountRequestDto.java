package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.ProductViewCount;

@Getter
@NoArgsConstructor
public class ProductViewCountRequestDto {
    private String productCode;
    private double viewCount;

    public static ProductViewCount toEntity(String productCode, double viewCount) {
        return ProductViewCount.builder()
                .productCode(productCode)
                .viewCount(viewCount)
                .build();
    }

    @Builder
    public ProductViewCountRequestDto(String productCode, double viewCount) {
        this.productCode = productCode;
        this.viewCount = viewCount;
    }
}
