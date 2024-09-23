package org.example.sivillage.purchase.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.purchase.vo.out.GetPurchaseCodeListResponseVo;

@Getter
@NoArgsConstructor
public class GetPurchaseCodeListResponseDto {

    private String purchaseCode;

    @Builder
    public GetPurchaseCodeListResponseDto(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public GetPurchaseCodeListResponseVo toVo() {
        return GetPurchaseCodeListResponseVo.builder()
                .purchaseCode(purchaseCode)
                .build();
    }
}
