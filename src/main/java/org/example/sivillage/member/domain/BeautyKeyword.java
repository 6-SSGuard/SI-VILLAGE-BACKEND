package org.example.sivillage.member.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@RequiredArgsConstructor
public enum BeautyKeyword {

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