package org.example.sivillage.BeautyInfo.domain.beautyenum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum BeautyKeyword {

    주름("주름"),
    리프팅("리프팅"),
    트러블("트러블"),
    보습("보습"),
    지성("지성"),
    미백("미백"),
    민감("민감"),
    기타("기타");

    private final String description;


}