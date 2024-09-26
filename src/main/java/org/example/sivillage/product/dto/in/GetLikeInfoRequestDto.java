package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.ProductLike;
import org.example.sivillage.product.domain.ProductLikeCount;

@Getter
@NoArgsConstructor
public class GetLikeInfoRequestDto {

    private String productCode;
    private String memberUuid;

    public static ProductLike updateToEntity(ProductLikeCount productLikeCount, String memberUuid, boolean liked) {
        return ProductLike.builder()
                .id(productLikeCount.getId())
                .productCode(productLikeCount.getProductCode())
                .memberUuid(memberUuid)
                .build();
    }

    public static ProductLike toEntity(String productCode, String memberUuid, boolean liked) {
        return ProductLike.builder()
                .productCode(productCode)
                .memberUuid(memberUuid)
                .liked(liked)
                .build();
    }

    @Builder
    public GetLikeInfoRequestDto(String productCode, String memberUuid) {
        this.productCode = productCode;
        this.memberUuid = memberUuid;
    }
}
