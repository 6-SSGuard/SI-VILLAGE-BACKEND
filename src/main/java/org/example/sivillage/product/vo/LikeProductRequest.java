package org.example.sivillage.product.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeProductRequest {
    private String productCode;
    private String memberUuid;
}
