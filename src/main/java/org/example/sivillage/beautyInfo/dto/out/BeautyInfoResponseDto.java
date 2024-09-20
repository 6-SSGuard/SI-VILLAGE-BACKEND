package org.example.sivillage.beautyInfo.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.beautyInfo.domain.BeautyInfo;
import org.example.sivillage.beautyInfo.vo.out.BeautyInfoResponseVo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BeautyInfoResponseDto {

    private String skinType;
    private String skinTone;
    private String scalpTone;
    private List<String> beautyKeyword;


    // string -> List
    public static List<String> convertListBeautykeyword(String beautyKeyword) {
        return Arrays.stream(beautyKeyword.split("\\s*,\\s*"))
                .collect(Collectors.toList());
    }

    public static BeautyInfoResponseDto from(BeautyInfo beautyInfo) {
        return BeautyInfoResponseDto.builder()
                .skinType(beautyInfo.getSkinType())
                .skinTone(beautyInfo.getSkinTone())
                .scalpTone(beautyInfo.getScalpTone())
                .beautyKeyword(convertListBeautykeyword(beautyInfo.getBeautyKeyword()))
                .build();
    }

    public static BeautyInfoResponseDto defaultResponse() {
        return BeautyInfoResponseDto.builder()
                .skinType(null)
                .skinTone(null)
                .scalpTone(null)
                .beautyKeyword(null)
                .build();
    }

    public BeautyInfoResponseVo toResponseVo(){
        return BeautyInfoResponseVo.builder()
                .skinType(skinType)
                .skinTone(skinTone)
                .scalpTone(scalpTone)
                .beautyKeyword(beautyKeyword)
               .build();
    }

    @Builder
    public BeautyInfoResponseDto(String skinType, String skinTone, String scalpTone, List <String> beautyKeyword){
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
    }
}

