package org.example.sivillage.member.dto.in;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.member.domain.memberenum.BeautyKeyword;
import org.example.sivillage.member.domain.memberenum.SkinType;
import org.example.sivillage.member.domain.memberenum.ScalpTone;
import org.example.sivillage.member.domain.memberenum.SkinTone;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class BeautyInfoRequestDto {

    @Schema(description = "피부타입", example = "DRY", required = true)
    @NotNull
    private SkinType skinType;

    @Schema(description = "피부톤", example = "COOL", required = true)
    @NotNull
    private SkinTone skinTone;

    @Schema(description = "두피타입", example = "DRY", required = true)
    @NotNull
    private ScalpTone scalpTone;

    @Schema(description = "뷰티키워드",  example = "[\"WRINKLES\", \"LIFTING\", \"TROUBLE\"]", required = true)
    @NotEmpty
    @Size(min = 3, max = 5, message = "뷰티키워드는 3개 이상 5개 이하로 선택 가능합니다.")
    private List<BeautyKeyword> beautyKeyword;


    // List -> string
    public String convertStringBeautyKeyword(List<BeautyKeyword> beautyKeyword) {
        return beautyKeyword.stream()
                .map(BeautyKeyword::name) // enum의 이름을 문자열로 변환
                .collect(Collectors.joining(", "));
    }


}
