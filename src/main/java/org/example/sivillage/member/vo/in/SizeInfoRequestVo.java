package org.example.sivillage.member.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.domain.memberenum.*;
import org.example.sivillage.member.dto.in.SizeInfoRequestDto;

@Getter
@NoArgsConstructor
public class SizeInfoRequestVo {

    @Schema(description = "키", example = "160", required = true)
    @NotNull
    private Integer height;

    @Schema(description = "몸무게", example = "48", required = true)
    @NotNull
    private Integer weight;

    @Schema(description = "상의 사이즈", example = "XS", required = true)
    @NotNull
    private TopSize topSize;

    @Schema(description = "하의 사이즈", example = "SIZE_24", required = true)
    @NotNull
    private BottomSize bottomSize;

    @Schema(description = "신발 사이즈", example = "SIZE_240", required = true)
    @NotNull
    private ShoeSize shoeSize;

    public static SizeInfoRequestDto toDto (SizeInfoRequestVo vo) {
        return SizeInfoRequestDto.builder()
                .height(vo.getHeight())
                .weight(vo.getWeight())
                .topSize(vo.getTopSize().toString())
                .bottomSize(vo.getBottomSize().getDescription())
                .shoeSize(vo.getShoeSize().getDescription())
                .build();
    }


}
