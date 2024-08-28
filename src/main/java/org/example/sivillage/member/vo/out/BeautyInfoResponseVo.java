package org.example.sivillage.member.vo.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.domain.memberenum.BeautyKeyword;
import org.example.sivillage.member.domain.memberenum.ScalpTone;
import org.example.sivillage.member.domain.memberenum.SkinTone;
import org.example.sivillage.member.domain.memberenum.SkinType;

import java.util.List;

@NoArgsConstructor
@Getter
public class BeautyInfoResponseVo {

    private SkinType skinType;
    private SkinTone skinTone;
    private ScalpTone scalpTone;
    private List<BeautyKeyword> beautyKeyword;

}
