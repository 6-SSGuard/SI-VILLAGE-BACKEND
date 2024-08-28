package org.example.sivillage.member.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.domain.memberenum.*;

@NoArgsConstructor
@Getter
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

    @Schema(description = "하의 사이즈", example = "24", required = true)
    @NotNull
    private BottomSize bottomSize;

    @Schema(description = "신발 사이즈", example = "240", required = true)
    @NotNull
    private ShoeSize shoeSize;

}
