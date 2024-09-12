package org.example.sivillage.productoption;

import lombok.Getter;

@Getter
public enum Size {  // size도 마찬가지로 클래스로 가져가자
    XXS, XS, S, M, L, XL, XXL, XXXL;
}
/** 상품 생성, 색상, 사이즈 등록 부분은 vendor 도메인으로 빼기
 * get관련 로직은 product로, 나머지쓰기 관련은 vendor로
 * productlike -> member
 * productOption -> vendor(벤더가 생성하므로)
 */