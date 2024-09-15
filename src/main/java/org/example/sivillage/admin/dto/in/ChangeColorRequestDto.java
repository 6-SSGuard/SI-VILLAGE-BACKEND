package org.example.sivillage.admin.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.Color;
import org.example.sivillage.admin.vo.in.ChangeColorRequestVo;

@Getter
@NoArgsConstructor
public class ChangeColorRequestDto {
    private Long colorId;
    private String colorName;
    private String colorCode;

    @Builder
    public ChangeColorRequestDto(Long colorId, String colorName, String colorCode) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public static ChangeColorRequestDto from(ChangeColorRequestVo vo) {
        return ChangeColorRequestDto.builder()
            .colorId(vo.getColorId())
            .colorName(vo.getColorName())
            .colorCode(vo.getColorCode())
            .build();
    }

    public Color updateEntity(Long id) {
        return new Color(
                id,
                colorName,
                colorCode
        );
    }
}
