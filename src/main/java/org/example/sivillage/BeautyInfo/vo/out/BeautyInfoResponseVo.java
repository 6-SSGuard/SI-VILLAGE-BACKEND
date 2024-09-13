package org.example.sivillage.BeautyInfo.vo.out;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeautyInfoResponseVo {

    private String skinType;
    private String skinTone;
    private String scalpTone;
    private List<String> beautyKeyword;

}
