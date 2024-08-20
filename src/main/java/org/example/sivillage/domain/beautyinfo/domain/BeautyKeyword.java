package org.example.sivillage.domain.beautyinfo.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BeautyKeyword {
    PORES("모공"),
    WRINKLES("주름"),
    LIFTING("리프팅"),
    TROUBLE("트러블"),
    MOISTURIZING("보습"),
    OILY("지성"),
    WHITENING("미백"),
    SENSITIVE("민감"),
    ETC("기타");

    private final String description;
}
