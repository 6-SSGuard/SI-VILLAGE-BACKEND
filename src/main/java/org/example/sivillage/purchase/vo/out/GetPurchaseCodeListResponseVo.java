package org.example.sivillage.purchase.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPurchaseCodeListResponseVo {

    private String purchaseCode;

    @Builder
    public GetPurchaseCodeListResponseVo(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }
}
