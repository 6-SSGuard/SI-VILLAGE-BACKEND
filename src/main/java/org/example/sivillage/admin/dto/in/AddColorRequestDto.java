package org.example.sivillage.admin.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.Color;
import org.example.sivillage.admin.vo.in.AddColorRequestVo;

@Getter
@NoArgsConstructor
public class AddColorRequestDto {

    private String colorName;
    private String colorCode;

    @Builder
    public AddColorRequestDto(String colorName, String colorCode) {
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public Color toEntity() {
        return Color.builder()
            .colorName(colorName)
            .colorCode(colorCode)
            .build();
    }

    public static AddColorRequestDto from(AddColorRequestVo vo) {
        return AddColorRequestDto.builder()
            .colorName(vo.getColorName())
            .colorCode(vo.getColorCode())
            .build();
    }
}
