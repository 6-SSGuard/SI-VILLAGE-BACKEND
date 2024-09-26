package org.example.sivillage.product.application;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.domain.ProductLike;
import org.example.sivillage.product.vo.out.GetLikeInfoResponseVo;

@Getter
@NoArgsConstructor
public class GetLikeInfoResponseDto {

    private boolean liked;

    @Builder
    public GetLikeInfoResponseDto(boolean liked) {
        this.liked = liked;
    }

    public static GetLikeInfoResponseDto from(boolean liked) {
        return GetLikeInfoResponseDto.builder()
                .liked(liked)
                .build();
    }

    public GetLikeInfoResponseVo toVo() {
        return GetLikeInfoResponseVo.builder()
                .liked(liked)
                .build();
    }
}
