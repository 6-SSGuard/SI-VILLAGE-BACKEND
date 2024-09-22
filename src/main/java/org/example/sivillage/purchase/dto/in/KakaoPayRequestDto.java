package org.example.sivillage.purchase.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.purchase.vo.in.KakaoPayRequestVo;

@Getter
@NoArgsConstructor
public class KakaoPayRequestDto {

    private String purchaseCode;

    @Builder
    public KakaoPayRequestDto(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public static KakaoPayRequestDto from(KakaoPayRequestVo vo) {
        return KakaoPayRequestDto.builder()
            .purchaseCode(vo.getPurchaseCode())
            .build();
    }

}
