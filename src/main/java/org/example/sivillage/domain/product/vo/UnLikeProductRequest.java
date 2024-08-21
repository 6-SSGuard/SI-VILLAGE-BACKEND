package org.example.sivillage.domain.product.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnLikeProductRequest {
    private Long productId;
    private Long memberId;
}
