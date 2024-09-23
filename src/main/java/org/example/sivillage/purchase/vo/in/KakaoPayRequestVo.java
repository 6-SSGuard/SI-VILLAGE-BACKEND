package org.example.sivillage.purchase.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoPayRequestVo {

    private String purchaseCode;

    public KakaoPayRequestVo(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }
}
