package org.example.sivillage.domain.product.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Color {
    RED("레드"),
    BLUE("블루"),
    GREEN("그린"),
    YELLOW("옐로우"),
    BLACK("블랙"),
    WHITE("화이트"),
    BEIGE("베이지"),
    GRAY("그레이"),
    BROWN("브라운"),
    PINK("핑크"),
    ORANGE("오렌지"),
    PURPLE("퍼플"),
    GOLD("골드"),
    SILVER("실버"),
    ETC("기타")
    ;



    private final String description;
}
