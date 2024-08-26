package org.example.sivillage.memberinfo.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ScalpTone {

    DRY("건성"),
    NEUTRAL("중성"),
    OILY("지성");

    private final String description;
}
