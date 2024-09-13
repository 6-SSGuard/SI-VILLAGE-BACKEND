package org.example.sivillage.BeautyInfo.dto.in;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;

@Getter
public class BeautyInfoRequestDto {

    private String skinType;

    private String skinTone;

    private String scalpTone;

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
    public BeautyInfoRequestDto(String skinType, String skinTone, String scalpTone, String beautyKeyword) {
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
    }

}

