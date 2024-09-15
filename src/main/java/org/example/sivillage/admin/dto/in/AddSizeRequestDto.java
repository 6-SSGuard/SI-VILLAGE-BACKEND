package org.example.sivillage.admin.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.Size;
import org.example.sivillage.admin.vo.in.AddSizeRequestVo;

@Getter
@NoArgsConstructor
public class AddSizeRequestDto {

    private String sizeName;
    private String sizeType;

    @Builder
    public AddSizeRequestDto(String sizeName, String sizeType) {
        this.sizeName = sizeName;
        this.sizeType = sizeType;
    }

    public Size toEntity() {
        return Size.builder()
            .sizeName(sizeName)
            .sizeType(sizeType)
            .build();
    }

    public static AddSizeRequestDto from(AddSizeRequestVo addSizeRequestVo) {
        return AddSizeRequestDto.builder()
            .sizeName(addSizeRequestVo.getSizeName())
            .sizeType(addSizeRequestVo.getSizeType())
            .build();
    }
}
