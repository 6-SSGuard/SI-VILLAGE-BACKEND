package org.example.sivillage.memberinfo.dto.in;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.memberinfo.domain.SkinType;
import org.example.sivillage.memberinfo.domain.ScalpTone;
import org.example.sivillage.memberinfo.domain.SkinTone;
import org.example.sivillage.member.domain.Member;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeautyInfoRequestDto {

    private Member member;

    private SkinType skinType;

    private SkinTone skinTone;

    private ScalpTone scalpTone;

    private String beautyKeyword;


}
