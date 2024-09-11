package org.example.sivillage.brand.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.vo.in.AddBrandRequestVo;

@Getter
@NoArgsConstructor
public class AddBrandRequestDto {
    private String brandEngName;
    private String brandKorName;

    @Builder
    public AddBrandRequestDto(String brandEngName, String brandKorName) {
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
    }

    public Brand toEntity() {
        return Brand.builder()
                .brandEngName(brandEngName)
                .brandKorName(brandKorName)
                .build();
    }

    public static AddBrandRequestDto from(AddBrandRequestVo addBrandRequestVo) {
        return AddBrandRequestDto.builder()
                .brandEngName(addBrandRequestVo.getBrandEngName())
                .brandKorName(addBrandRequestVo.getBrandKorName())
                .build();
    }
}
