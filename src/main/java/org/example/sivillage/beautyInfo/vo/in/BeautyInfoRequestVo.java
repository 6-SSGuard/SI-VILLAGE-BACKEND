package org.example.sivillage.beautyInfo.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.example.sivillage.beautyInfo.dto.in.BeautyInfoRequestDto;
import java.util.List;


@Getter
public class BeautyInfoRequestVo {

    @Schema(description = "피부타입", example = "건성", required = true)
    @NotNull
    private String skinType;

    @Schema(description = "피부톤", example = "쿨톤", required = true)
    @NotNull
    private String skinTone;

    @Schema(description = "두피타입", example = "건성", required = true)
    @NotNull
    private String scalpTone;

    @Schema(description = "뷰티키워드",  example = "[\"주름\", \"민감\", \"트러블\"]", required = true)
    @NotEmpty
    @Size(min = 3, max = 5, message = "뷰티키워드는 3개 이상 5개 이하로 선택 가능합니다.")
    private List<String> beautyKeyword;

    // List -> string
    public String convertStringBeautyKeyword(List<String>beautyKeyword) {
        return String.join(", ", beautyKeyword);
    }

    public static BeautyInfoRequestDto toDto (BeautyInfoRequestVo beautyInfoRequestVo) {
        return BeautyInfoRequestDto.builder()
                .skinType(beautyInfoRequestVo.getSkinType())
                .skinTone(beautyInfoRequestVo.getSkinTone())
                .scalpTone(beautyInfoRequestVo.getScalpTone())
                .beautyKeyword(beautyInfoRequestVo.convertStringBeautyKeyword(beautyInfoRequestVo.getBeautyKeyword()))
                .build();
    }

}
