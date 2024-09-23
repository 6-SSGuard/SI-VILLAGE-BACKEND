package org.example.sivillage.purchase.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Purchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String purchaseCode;

    @Column(nullable = false)
    private String shippingMessage;

    @Column(nullable = false)
    private Long shippingAddressId;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private Integer totalPriceBeforeDiscount;

    @Column(nullable = false)
    private Integer totalPriceAfterDiscount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    @Builder
    public Purchase(Long id, String purchaseCode, String shippingMessage, Long shippingAddressId, String memberUuid,
                    Integer totalPriceBeforeDiscount, Integer totalPriceAfterDiscount, PayStatus payStatus) {
        this.id = id;
        this.purchaseCode = purchaseCode;
        this.shippingMessage = shippingMessage;
        this.shippingAddressId = shippingAddressId;
        this.memberUuid = memberUuid;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
        this.payStatus = payStatus;
    }

    @Builder
    public Purchase(String purchaseCode, String shippingMessage, Long shippingAddressId, String memberUuid,
                    Integer totalPriceBeforeDiscount, Integer totalPriceAfterDiscount, PayStatus payStatus) {
        this.purchaseCode = purchaseCode;
        this.shippingMessage = shippingMessage;
        this.shippingAddressId = shippingAddressId;
        this.memberUuid = memberUuid;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
        this.payStatus = payStatus;
    }
}
