package org.example.sivillage.domain.beautyinfo.vo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.domain.beautyinfo.domain.BeautyKeyword;
import org.example.sivillage.domain.beautyinfo.domain.ScalpTone;
import org.example.sivillage.domain.beautyinfo.domain.SkinTone;
import org.example.sivillage.domain.beautyinfo.domain.SkinType;
import org.example.sivillage.domain.beautyinfo.dto.BeautyInfoRequest;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class BeautyInfoRequestVo {

    @Schema(description = "피부타입", example = "DRY", required = true)
    @NotNull
    private SkinType skinType;

    @Schema(description = "피부톤", example = "COOL", required = true)
    @NotNull
    private SkinTone skinTone;

    @Schema(description = "두피타입", example = "DRY", required = true)
    @NotNull
    private ScalpTone scalpTone;

    @Schema(description = "뷰티키워드",  example = "[\"WRINKLES\", \"LIFTING\"]", required = true)
    @NotEmpty
    private List<BeautyKeyword> beautyKeyword;


    // List 를 string 으로 변환
    public String convertBeautyKeyword(List<BeautyKeyword> list) {
        return list.stream()
                .map(BeautyKeyword::name) // enum의 이름을 문자열로 변환
                .collect(Collectors.joining(", "));
    }

}
