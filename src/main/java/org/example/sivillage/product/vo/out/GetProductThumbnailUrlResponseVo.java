package org.example.sivillage.product.vo.out;

import lombok.*;

@Getter
@NoArgsConstructor
public class GetProductThumbnailUrlResponseVo {
    private String thumbnailUrl;

    @Builder
    public GetProductThumbnailUrlResponseVo(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
