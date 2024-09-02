package org.example.sivillage.BeautyInfo.vo.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sivillage.BeautyInfo.domain.beautyenum.BeautyKeyword;
import org.example.sivillage.BeautyInfo.domain.beautyenum.ScalpTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BeautyInfoResponseVo {

    private SkinType skinType;
    private SkinTone skinTone;
    private ScalpTone scalpTone;
    private List<BeautyKeyword> beautyKeyword;

}
