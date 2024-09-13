package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetLikeCountResponseVo {

    private int likeCount;

    @Builder
    public GetLikeCountResponseVo(int likeCount) {
        this.likeCount = likeCount;
    }
}
