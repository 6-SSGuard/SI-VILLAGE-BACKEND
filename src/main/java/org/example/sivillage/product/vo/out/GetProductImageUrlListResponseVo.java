package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductImageUrlListResponseVo {

    private String imageUrl;

    @Builder
    public GetProductImageUrlListResponseVo(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
