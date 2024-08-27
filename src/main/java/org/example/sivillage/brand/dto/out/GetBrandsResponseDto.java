package org.example.sivillage.brand.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.domain.Brand;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBrandsResponseDto {
    private Long brandId;
    private String brandEngName;
    private String brandKorName;

    public static GetBrandsResponseDto toDto(Brand brand) {
        return GetBrandsResponseDto.builder()
                .brandId(brand.getBrandId())
                .brandEngName(brand.getBrandEngName())
                .brandKorName(brand.getBrandKorName())
                .build();
    }
}
