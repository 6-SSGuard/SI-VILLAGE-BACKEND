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
    private String brandIndexLetter;
    private String brandIndexLetterKor;

    @Builder
    public AddBrandRequestDto(String brandEngName, String brandKorName, String brandIndexLetter, String brandIndexLetterKor) {
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
        this.brandIndexLetter = brandIndexLetter;
        this.brandIndexLetterKor = brandIndexLetterKor;
    }

    public Brand toEntity() {
        return Brand.builder()
                .brandEngName(brandEngName)
                .brandKorName(brandKorName)
                .brandIndexLetter(brandIndexLetter)
                .brandIndexLetterKor(brandIndexLetterKor)
                .build();
    }

    public static AddBrandRequestDto from(AddBrandRequestVo addBrandRequestVo) {
        return AddBrandRequestDto.builder()
                .brandEngName(addBrandRequestVo.getBrandEngName())
                .brandKorName(addBrandRequestVo.getBrandKorName())
                .brandIndexLetter(addBrandRequestVo.getBrandIndexLetter())
                .brandIndexLetterKor(addBrandRequestVo.getBrandIndexLetterKor())
                .build();
    }
}
