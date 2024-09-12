package org.example.sivillage.product.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.vo.out.GetProductThumbnailUrlResponseVo;

@Getter
@NoArgsConstructor
public class GetProductThumbnailUrlResponseDto {
    private String thumbnailUrl;

    @Builder
    public GetProductThumbnailUrlResponseDto(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public static GetProductThumbnailUrlResponseDto from(String thumbnailUrl) {
        return GetProductThumbnailUrlResponseDto.builder()
                .thumbnailUrl(thumbnailUrl)
                .build();
    }

    public GetProductThumbnailUrlResponseVo toVo() {
        return GetProductThumbnailUrlResponseVo.builder()
                .thumbnailUrl(thumbnailUrl)
                .build();
    }
}
