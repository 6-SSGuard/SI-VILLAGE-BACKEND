package org.example.sivillage.purchase.application;

import org.example.sivillage.purchase.dto.in.KakaoPayRequestDto;
import org.example.sivillage.purchase.dto.out.ApproveResponse;
import org.example.sivillage.purchase.dto.out.ReadyResponse;

public interface PayService {

    public ReadyResponse payReady(String memberUuid, KakaoPayRequestDto kakaoPayRequestDto);

    public ApproveResponse payApprove(String tid, String pgToken, String partner_order_id, String partner_user_id);
}
