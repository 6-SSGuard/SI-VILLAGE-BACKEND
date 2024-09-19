package org.example.sivillage.brand.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.vo.out.GetBrandNameResponseVo;

@NoArgsConstructor
@Getter
public class GetBrandNameResponseDto {

    private String brandEngName;
    private String brandKorName;

    @Builder
    public GetBrandNameResponseDto(String brandEngName, String brandKorName) {
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
    }

    public GetBrandNameResponseVo toVo() {
        return GetBrandNameResponseVo.builder()
                .brandEngName(brandEngName)
                .brandKorName(brandKorName)
                .build();
    }
}
