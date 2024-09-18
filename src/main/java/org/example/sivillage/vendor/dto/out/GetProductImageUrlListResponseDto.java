package org.example.sivillage.vendor.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.product.vo.out.GetProductImageUrlListResponseVo;

@Getter
@NoArgsConstructor
public class GetProductImageUrlListResponseDto {
    private String imageUrl;

    @Builder
    public GetProductImageUrlListResponseDto(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static GetProductImageUrlListResponseDto from(String imageUrl) {
        return GetProductImageUrlListResponseDto.builder()
                .imageUrl(imageUrl)
                .build();
    }

    public GetProductImageUrlListResponseVo toVo() {
        return GetProductImageUrlListResponseVo.builder()
                .imageUrl(imageUrl)
                .build();
    }
}
