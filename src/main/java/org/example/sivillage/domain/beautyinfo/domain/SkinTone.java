package org.example.sivillage.domain.beautyinfo.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SkinTone {
    COOL("쿨톤"),
    WARM("웜톤"),
    NEUTRAL("뉴트럴톤");

    private final String description;
}
