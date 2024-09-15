package org.example.sivillage.admin.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.Size;
import org.example.sivillage.admin.vo.out.GetSizeResponseVo;

@Getter
@NoArgsConstructor
public class GetSizeResponseDto {

    private Long id;
    private String sizeName;
    private String sizeType;

    @Builder
    public GetSizeResponseDto(Long id, String sizeName, String sizeType) {
        this.id = id;
        this.sizeName = sizeName;
        this.sizeType = sizeType;
    }

    public static GetSizeResponseDto from(Size size) {
        return GetSizeResponseDto.builder()
            .id(size.getId())
            .sizeName(size.getSizeName())
            .sizeType(size.getSizeType())
            .build();
    }

    public GetSizeResponseVo toVo() {
        return GetSizeResponseVo.builder()
            .id(id)
            .sizeName(sizeName)
            .sizeType(sizeType)
            .build();
    }
}
