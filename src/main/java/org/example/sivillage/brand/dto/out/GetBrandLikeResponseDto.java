package org.example.sivillage.brand.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.vo.out.GetBrandLikeResponseVo;

@NoArgsConstructor
@Getter
public class GetBrandLikeResponseDto {

    private Boolean like;

    @Builder
    public GetBrandLikeResponseDto(Boolean like) {
        this.like = like;
    }

    public GetBrandLikeResponseVo toVo() {
        return GetBrandLikeResponseVo.builder()
                .like(like)
                .build();
    }
}
