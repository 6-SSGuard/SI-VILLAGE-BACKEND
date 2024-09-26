package org.example.sivillage.product.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetLikeInfoRequestDto {

    private String productCode;
    private String memberUuid;

    @Builder
    public GetLikeInfoRequestDto(String productCode, String memberUuid) {
        this.productCode = productCode;
        this.memberUuid = memberUuid;
    }
}
