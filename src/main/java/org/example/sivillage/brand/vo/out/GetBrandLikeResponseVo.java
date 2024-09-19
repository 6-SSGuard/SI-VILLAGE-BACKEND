package org.example.sivillage.brand.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetBrandLikeResponseVo {

    private Boolean like;

    @Builder
    public GetBrandLikeResponseVo(Boolean like) {
        this.like = like;
    }
}
