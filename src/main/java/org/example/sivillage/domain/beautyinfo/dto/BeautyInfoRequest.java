package org.example.sivillage.domain.beautyinfo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.domain.beautyinfo.domain.ScalpTone;
import org.example.sivillage.domain.beautyinfo.domain.SkinTone;
import org.example.sivillage.domain.beautyinfo.domain.SkinType;
import org.example.sivillage.domain.beautyinfo.vo.BeautyInfoRequestVo;
import org.example.sivillage.domain.member.domain.Member;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeautyInfoRequest {

    private Member member;

    private SkinType skinType;

    private SkinTone skinTone;

    private ScalpTone scalpTone;

    private String beautyKeyword;

    public static BeautyInfoRequest toDto(BeautyInfoRequestVo vo, Member member) {
        return BeautyInfoRequest.builder()
                .skinType(vo.getSkinType())
                .skinTone(vo.getSkinTone())
                .scalpTone(vo.getScalpTone())
                .beautyKeyword(vo.convertBeautyKeyword(vo.getBeautyKeyword()))
                .member(member)
                .build();
    }

}
