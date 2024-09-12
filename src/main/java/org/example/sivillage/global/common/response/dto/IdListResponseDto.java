package org.example.sivillage.global.common.response.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;

@NoArgsConstructor
@Getter
public class IdListResponseDto {

    private Long id;

    @Builder
    public IdListResponseDto(Long id) {
        this.id = id;
    }

    public static IdListResponseDto from(IdListResponseVo idListResponseVo) {
        return IdListResponseDto.builder()
                .id(idListResponseVo.getId())
                .build();
    }
}
