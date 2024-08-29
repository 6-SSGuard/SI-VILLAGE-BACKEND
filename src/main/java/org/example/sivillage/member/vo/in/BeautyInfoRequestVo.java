package org.example.sivillage.member.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.domain.memberenum.BeautyKeyword;
import org.example.sivillage.member.domain.memberenum.ScalpTone;
import org.example.sivillage.member.domain.memberenum.SkinTone;
import org.example.sivillage.member.domain.memberenum.SkinType;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeautyInfoRequestVo {

    @Schema(description = "피부타입", example = "건성", required = true)
    @NotNull
    private SkinType skinType;

    @Schema(description = "피부톤", example = "쿨톤", required = true)
    @NotNull
    private SkinTone skinTone;

    @Schema(description = "두피타입", example = "건성", required = true)
    @NotNull
    private ScalpTone scalpTone;

    @Schema(description = "뷰티키워드",  example = "[\"주름\", \"민감\", \"트러블\"]", required = true)
    @NotEmpty
    @Size(min = 3, max = 5, message = "뷰티키워드는 3개 이상 5개 이하로 선택 가능합니다.")
    private List<BeautyKeyword> beautyKeyword;

    // List -> string
    public String convertStringBeautyKeyword(List<BeautyKeyword> beautyKeyword) {
        return beautyKeyword.stream()
                .map(BeautyKeyword::name) // enum의 이름을 문자열로 변환
                .collect(Collectors.joining(", "));
    }


    public static BeautyInfoRequestDto toDto (BeautyInfoRequestVo vo) {
        return BeautyInfoRequestDto.builder()
                .skinType(vo.getSkinType())
                .skinTone(vo.getSkinTone())
                .scalpTone(vo.getScalpTone())
                .beautyKeyword(vo.convertStringBeautyKeyword(vo.getBeautyKeyword()))
                .build();
    }

}
