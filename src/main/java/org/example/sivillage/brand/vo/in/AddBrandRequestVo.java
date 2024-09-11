package org.example.sivillage.brand.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddBrandRequestVo {
    @NotBlank(message = "브랜드 영어 이름은 필수입니다.")
    @Schema(description = "브랜드 영어 이름", example = "brand name")
    private String brandEngName;

    @NotBlank(message = "브랜드 한글 이름은 필수입니다.")
    @Schema(description = "브랜드 한글 이름", example = "브랜드 명")
    private String brandKorName;

    @Builder
    public AddBrandRequestVo(String brandEngName, String brandKorName) {
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
    }
}
