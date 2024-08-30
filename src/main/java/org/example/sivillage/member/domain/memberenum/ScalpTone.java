package org.example.sivillage.member.domain.memberenum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScalpTone {

    건성("건성"),
    중성("중성"),
    지성("지성");

    private final String description;
}
