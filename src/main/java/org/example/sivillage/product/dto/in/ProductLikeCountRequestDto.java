package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.ProductLikeCount;

@Getter
@NoArgsConstructor
public class ProductLikeCountRequestDto {

    private String productCode;

    private Long likeCount;


    public static ProductLikeCount toEntity(String productCode, Long likeCount) {
        return ProductLikeCount.builder()
                .productCode(productCode)
                .likeCount(likeCount)
                .build();
    }

    public static ProductLikeCount updateToEntity(ProductLikeCount productLikeCount, Long likeCount) {
        return ProductLikeCount.builder()
                .id(productLikeCount.getId())
                .productCode(productLikeCount.getProductCode())
                .likeCount(likeCount)
                .build();
    }

    @Builder
    public ProductLikeCountRequestDto(String productCode, Long likeCount) {
        this.productCode = productCode;
        this.likeCount = likeCount;
    }

}
