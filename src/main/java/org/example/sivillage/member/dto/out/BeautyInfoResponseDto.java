package org.example.sivillage.member.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.member.domain.*;
import org.example.sivillage.member.domain.memberenum.ScalpTone;
import org.example.sivillage.member.domain.memberenum.SkinTone;
import org.example.sivillage.member.domain.memberenum.SkinType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
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

    public static BeautyInfoResponseDto toDto (BeautyInfo beautyInfo) {
        return BeautyInfoResponseDto.builder()
                .skinType(beautyInfo.getSkinType())
                .skinTone(beautyInfo.getSkinTone())
                .scalpTone(beautyInfo.getScalpTone())
                .beautyKeyword(convertListBeautykeyword(beautyInfo.getBeautyKeyword())) // Assuming BeautyKeyword is a List<BeautyKeyword>
                .build();
    }

}

