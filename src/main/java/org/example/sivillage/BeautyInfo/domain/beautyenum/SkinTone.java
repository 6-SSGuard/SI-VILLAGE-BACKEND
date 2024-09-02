package org.example.sivillage.BeautyInfo.domain.beautyenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SkinTone {

    쿨톤("쿨톤"),
    웜톤("웜톤"),
    뉴트럴톤("뉴트럴톤");

    private final String description;
}
