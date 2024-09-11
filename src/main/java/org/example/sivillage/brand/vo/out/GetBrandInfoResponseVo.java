package org.example.sivillage.brand.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetBrandInfoResponseVo {

    private String brandEngName;
    private String brandKorName;

    @Builder
    public GetBrandInfoResponseVo(String brandEngName, String brandKorName) {
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
    }
}
