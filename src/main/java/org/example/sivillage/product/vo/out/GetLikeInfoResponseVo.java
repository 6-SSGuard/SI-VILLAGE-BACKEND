package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetLikeInfoResponseVo {

    private boolean liked;

    @Builder
    public GetLikeInfoResponseVo(boolean liked) {
        this.liked = liked;
    }
}
