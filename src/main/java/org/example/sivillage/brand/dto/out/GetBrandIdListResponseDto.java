package org.example.sivillage.brand.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.vo.out.GetBrandIdListResponseVo;

@Getter
@NoArgsConstructor
public class GetBrandIdListResponseDto {
    private Long brandId;

    @Builder
    public GetBrandIdListResponseDto(Long brandId) {
        this.brandId = brandId;
    }

    public GetBrandIdListResponseVo toVo() {
        return GetBrandIdListResponseVo.builder()
                .brandId(brandId)
                .build();
    }
}
