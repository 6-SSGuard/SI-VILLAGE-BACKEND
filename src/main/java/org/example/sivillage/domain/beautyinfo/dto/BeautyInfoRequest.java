package org.example.sivillage.domain.beautyinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sivillage.domain.beautyinfo.domain.ScalpTone;
import org.example.sivillage.domain.beautyinfo.domain.SkinTone;
import org.example.sivillage.domain.beautyinfo.domain.SkinType;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BeautyInfoRequest {

    @Schema(description = "피부타입", example = "건성", required = true)
    @NotBlank(message = "피부타입을 선택해 주세요.")
    private SkinType skinType;

    @Schema(description = "피부톤", example = "쿨톤", required = true)
    @NotBlank(message = "피부톤을 선택해 주세요.")
    private SkinTone skinTone;

    @Schema(description = "두피타입", example = "건성", required = true)
    @NotBlank(message = "두피타입을 선택해 주세요.")
    private ScalpTone scalpTone;

    @Schema(description = "뷰티키워드", example = "모공 + 트러블 등등", required = true)
    @NotBlank(message = "뷰티 키워드를 선택해 주세요.")
    @Size(max = 5, message = "최대 5개의 키워드만 선택할 수 있습니다.")
    private List<String> beautyKeyword;

    }
