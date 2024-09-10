package org.example.sivillage.sizeinfo.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.sizeinfo.dto.in.SizeInfoRequestDto;

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
    private String topSize;

    @Schema(description = "하의 사이즈", example = "24", required = true)
    @NotNull
    private String bottomSize;

    @Schema(description = "신발 사이즈", example = "240", required = true)
    @NotNull
    private String shoeSize;

    public static SizeInfoRequestDto toDto (SizeInfoRequestVo sizeInfoRequestVo) {
        return SizeInfoRequestDto.builder()
                .height(sizeInfoRequestVo.getHeight())
                .weight(sizeInfoRequestVo.getWeight())
                .topSize(sizeInfoRequestVo.getTopSize())
                .bottomSize(sizeInfoRequestVo.getBottomSize())
                .shoeSize(sizeInfoRequestVo.getShoeSize())
                .build();
    }

}
