package org.example.sivillage.BeautyInfo.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;
import org.example.sivillage.BeautyInfo.domain.beautyenum.ScalpTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinType;
import org.example.sivillage.BeautyInfo.vo.out.BeautyInfoResponseVo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BeautyInfoResponseDto {

    private SkinType skinType;
    private SkinTone skinTone;
    private ScalpTone scalpTone;
    private List<String> beautyKeyword;


    // string -> List
    public static List<String> convertListBeautykeyword(String beautyKeyword) {
        return Arrays.stream(beautyKeyword.split("\\s*,\\s*"))
                .collect(Collectors.toList());
    }

    public static BeautyInfoResponseDto from (BeautyInfo beautyInfo) {
        return BeautyInfoResponseDto.builder()
                .skinType(beautyInfo.getSkinType())
                .skinTone(beautyInfo.getSkinTone())
                .scalpTone(beautyInfo.getScalpTone())
                .beautyKeyword(convertListBeautykeyword(beautyInfo.getBeautyKeyword())) // Assuming BeautyKeyword is a List<BeautyKeyword>
                .build();
    }

    public static BeautyInfoResponseDto emptyResponse() {
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
    public BeautyInfoResponseDto(SkinType skinType, SkinTone skinTone, ScalpTone scalpTone, List<String> beautyKeyword){
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
    }
}

