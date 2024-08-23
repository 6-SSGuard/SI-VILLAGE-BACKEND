package org.example.sivillage.domain.beautyinfo.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ScalpTone {

    DRY("건성"),
    NEUTRAL("중성"),
    OILY("지성");

    private final String description;
}
