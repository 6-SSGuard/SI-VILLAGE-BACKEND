package org.example.sivillage.member.domain.memberenum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum SkinTone {

    COOL("쿨톤"),
    WARM("웜톤"),
    NEUTRAL("뉴트럴톤");

    private final String description;
}
