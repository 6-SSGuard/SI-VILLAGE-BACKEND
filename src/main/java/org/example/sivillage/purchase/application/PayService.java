package org.example.sivillage.purchase.application;

import org.example.sivillage.purchase.dto.out.ApproveResponse;
import org.example.sivillage.purchase.dto.out.ReadyResponse;

public interface PayService {

    public ReadyResponse payReady(String name, int totalPrice);

    public ApproveResponse payApprove(String tid, String pgToken);
}
