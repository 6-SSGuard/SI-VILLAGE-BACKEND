package org.example.sivillage.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private Long productOptionId;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private boolean selected;

    public void changeSelected() {
        this.selected = !this.selected;
    }

    @Builder
    public Cart(Long id, String memberUuid, String productCode, Long productOptionId, Integer amount, boolean selected) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
        this.selected = selected;
    }

    @Builder
    public Cart(String memberUuid, String productCode, Long productOptionId, Integer amount, boolean selected) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
        this.selected = selected;
    }

}
