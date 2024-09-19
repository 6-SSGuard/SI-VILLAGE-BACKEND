package org.example.sivillage.review.domain;

import lombok.Getter;
import org.example.sivillage.beautyInfo.domain.BeautyInfo;
import org.example.sivillage.sizeinfo.domain.SizeInfo;

@Getter
public enum CategoryType {

    // 소 -> 대 카테고리 순으로 키워드 추가하기

    MAKEUP("메이크업") {
        @Override
        public String getInfo(BeautyInfo beautyInfo, SizeInfo sizeInfo) {
            return beautyInfo.getSkinTone() != null ? beautyInfo.getSkinTone() : "";
        }
    },
    HAIR_CARE("헤어케어") {
        @Override
        public String getInfo(BeautyInfo beautyInfo, SizeInfo sizeInfo) {
            return beautyInfo.getScalpTone() != null ? beautyInfo.getScalpTone(): "";
        }
    },
    BEAUTY("뷰티") {
        @Override
        public String getInfo(BeautyInfo beautyInfo, SizeInfo sizeInfo) {
            return beautyInfo.getSkinType() != null ? beautyInfo.getSkinType() : "";
        }
    },
    SHOES("슈즈") {
        @Override
        public String getInfo(BeautyInfo beautyInfo, SizeInfo sizeInfo) {
            return sizeInfo.getShoeSize() != null ? sizeInfo.getShoeSize() : "";
        }
    },
    OTHERS("기타") {
        @Override
        public String getInfo(BeautyInfo beautyInfo, SizeInfo sizeInfo) {
            return sizeInfo.getTopSize() != null
                    ? "키: " + sizeInfo.getHeight() + "cm, 몸무게: " + sizeInfo.getWeight() + "kg, 평소 사이즈: " + sizeInfo.getTopSize()
                    : "";
        }

    };

    private final String keyword;

    CategoryType(String keyword) {
        this.keyword = keyword;
    }

  public abstract String getInfo(BeautyInfo beautyInfo, SizeInfo sizeInfo);

    // 카테고리 경로에 따라 Enum을 반환하는 메소드
    public static CategoryType fromCategoryPath(String categoryPath) {
        for (CategoryType type : values()) {
            if (categoryPath.contains(type.getKeyword())) {
                return type;
            }
        }
        return OTHERS; // 기본적으로 기타 카테고리 처리
    }
}