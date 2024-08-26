package org.example.sivillage.domain.product.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Color { // 이것도 string으로? 특정 색상 관련 관리자페이지에서 색상등록시에 필터링될 수 있는 색상과 자체 색상을 함께 입력할 수 있도록 하기, 클래스로 가져가기
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
