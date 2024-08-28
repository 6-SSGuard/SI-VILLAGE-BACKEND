package org.example.sivillage.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeProductRequestVo {
    private String productCode;
    private String memberUuid;
}
