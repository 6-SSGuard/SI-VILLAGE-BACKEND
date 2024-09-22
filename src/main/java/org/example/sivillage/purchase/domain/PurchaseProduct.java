package org.example.sivillage.purchase.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_product_id")
    private Long id;

    @Column(nullable = false)
    private String purchaseCode;

    @Column(nullable = false)
    private String productCode;

    private Long productOptionId;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Integer chargedPrice;

    @Builder
    public PurchaseProduct(Long id, String purchaseCode, String productCode, Long productOptionId, Integer amount,
                           Integer chargedPrice) {
        this.id = id;
        this.purchaseCode = purchaseCode;
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
        this.chargedPrice = chargedPrice;
    }

    @Builder
    public PurchaseProduct(String purchaseCode, String productCode, Long productOptionId, Integer amount,
                           Integer chargedPrice) {
        this.purchaseCode = purchaseCode;
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
        this.chargedPrice = chargedPrice;
    }
}
