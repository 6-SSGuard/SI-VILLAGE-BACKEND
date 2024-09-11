package org.example.sivillage.brand.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetBrandIdListResponseVo {
    private Long brandId;

    @Builder
    public GetBrandIdListResponseVo(Long brandId) {
        this.brandId = brandId;
    }
}
