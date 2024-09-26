package org.example.sivillage.product.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetLikeCountResponseVo {

    private Long likeCount;

    @Builder
    public GetLikeCountResponseVo(Long likeCount) {
        this.likeCount = likeCount;
    }
}
