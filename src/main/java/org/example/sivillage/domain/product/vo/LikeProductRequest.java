package org.example.sivillage.domain.product.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeProductRequest {
    private Long productId;
    private Long memberId;
}
