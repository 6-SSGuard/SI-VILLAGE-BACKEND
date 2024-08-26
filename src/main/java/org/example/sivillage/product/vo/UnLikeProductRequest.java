package org.example.sivillage.product.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnLikeProductRequest {
    private String productCode;
    private String memberUuid;
}
