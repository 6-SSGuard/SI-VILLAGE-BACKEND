package org.example.sivillage.admin.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.Color;
import org.example.sivillage.admin.vo.out.GetColorResponseVo;

@Getter
@NoArgsConstructor
public class GetColorResponseDto {

    private Long colorId;
    private String colorName;
    private String colorCode;

    @Builder
    public GetColorResponseDto(Long colorId, String colorName, String colorCode) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public static GetColorResponseDto from(Color color) {
        return GetColorResponseDto.builder()
            .colorId(color.getId())
            .colorName(color.getColorName())
            .colorCode(color.getColorCode())
            .build();
    }

    public GetColorResponseVo toVo() {
        return GetColorResponseVo.builder()
            .colorId(colorId)
            .colorName(colorName)
            .colorCode(colorCode)
            .build();
    }
}
