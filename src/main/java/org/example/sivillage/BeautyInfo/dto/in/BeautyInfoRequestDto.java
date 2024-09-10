package org.example.sivillage.BeautyInfo.dto.in;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinType;
import org.example.sivillage.BeautyInfo.domain.beautyenum.ScalpTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinTone;

@Getter
public class BeautyInfoRequestDto {

    private SkinType skinType;

    private SkinTone skinTone;

    private ScalpTone scalpTone;

    private String beautyKeyword;


    public BeautyInfo toEntity(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid) {
        return BeautyInfo.builder()
                .skinType(beautyInfoRequestDto.getSkinType())
                .skinTone(beautyInfoRequestDto.getSkinTone())
                .scalpTone(beautyInfoRequestDto.getScalpTone())
                .beautyKeyword(beautyInfoRequestDto.getBeautyKeyword())
                .memberUuid(memberUuid)
                .build();
    }

    public BeautyInfo updateToEntity(BeautyInfoRequestDto beautyInfoRequestDto, BeautyInfo beautyInfo) {
        return BeautyInfo.builder()
                .id(beautyInfo.getId())
                .skinType(beautyInfoRequestDto.getSkinType())
                .skinTone(beautyInfoRequestDto.getSkinTone())
                .scalpTone(beautyInfoRequestDto.getScalpTone())
                .beautyKeyword(beautyInfoRequestDto.getBeautyKeyword())
                .memberUuid(beautyInfo.getMemberUuid())
                .build();
    }

    @Builder
    public BeautyInfoRequestDto(SkinType skinType, SkinTone skinTone, ScalpTone scalpTone, String beautyKeyword) {
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
    }

}

