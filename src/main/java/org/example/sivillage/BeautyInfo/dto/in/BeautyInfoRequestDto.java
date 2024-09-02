package org.example.sivillage.BeautyInfo.dto.in;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinType;
import org.example.sivillage.BeautyInfo.domain.beautyenum.ScalpTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinTone;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeautyInfoRequestDto {

    private SkinType skinType;

    private SkinTone skinTone;

    private ScalpTone scalpTone;

    private String beautyKeyword;


}

