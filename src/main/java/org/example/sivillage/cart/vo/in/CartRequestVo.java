package org.example.sivillage.cart.vo.in;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestVo {

    private String productCode;

    private Long productOptionId;

    private Integer amount;

    public CartRequestVo(String productCode, Long productOptionId, Integer amount) {
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
    }
}
