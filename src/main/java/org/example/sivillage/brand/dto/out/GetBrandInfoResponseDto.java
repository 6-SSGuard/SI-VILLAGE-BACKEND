package org.example.sivillage.brand.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.vo.out.GetBrandInfoResponseVo;

@NoArgsConstructor
@Getter
public class GetBrandInfoResponseDto {

    private String brandEngName;
    private String brandKorName;

    @Builder
    public GetBrandInfoResponseDto(String brandEngName, String brandKorName) {
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
    }

    public GetBrandInfoResponseVo toVo() {
        return GetBrandInfoResponseVo.builder()
                .brandEngName(brandEngName)
                .brandKorName(brandKorName)
                .build();
    }
}
