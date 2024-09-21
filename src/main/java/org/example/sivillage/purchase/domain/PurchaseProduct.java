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
    private String productCode;

    private Long productOptionId;

    @Column(nullable = false)
    private Integer amount;

    @Builder
    public PurchaseProduct(Long id, String productCode, Long productOptionId, Integer amount) {
        this.id = id;
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
    }

    @Builder
    public PurchaseProduct(String productCode, Long productOptionId, Integer amount) {
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
    }
}
