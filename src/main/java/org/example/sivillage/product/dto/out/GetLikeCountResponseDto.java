package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.vo.out.GetLikeCountResponseVo;

@Getter
@NoArgsConstructor
public class GetLikeCountResponseDto {

    private Long likeCount;

    @Builder
    public GetLikeCountResponseDto(Long likeCount) {
        this.likeCount = likeCount;
    }

    public static GetLikeCountResponseDto from(Long likeCount) {
        return GetLikeCountResponseDto.builder()
                .likeCount(likeCount)
                .build();
    }

    public GetLikeCountResponseVo toVo() {
        return GetLikeCountResponseVo.builder()
                .likeCount(likeCount)
                .build();
    }
}