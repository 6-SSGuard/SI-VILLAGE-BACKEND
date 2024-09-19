package org.example.sivillage.admin.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.Size;
import org.example.sivillage.admin.vo.in.ChangeSizeRequestVo;

@Getter
@NoArgsConstructor
public class ChangeSizeRequestDto {

    private Long id;
    private String sizeName;
    private String sizeType;

    @Builder
    public ChangeSizeRequestDto(Long id, String sizeName, String sizeType) {
        this.id = id;
        this.sizeName = sizeName;
        this.sizeType = sizeType;
    }

    public static ChangeSizeRequestDto from(ChangeSizeRequestVo changeSizeRequestVo) {
        return ChangeSizeRequestDto.builder()
            .id(changeSizeRequestVo.getId())
            .sizeName(changeSizeRequestVo.getSizeName())
            .sizeType(changeSizeRequestVo.getSizeType())
            .build();
    }

    public Size updateEntity(Long id) {
        return Size.builder()
            .id(id)
            .sizeName(sizeName)
            .sizeType(sizeType)
            .build();
    }
}
