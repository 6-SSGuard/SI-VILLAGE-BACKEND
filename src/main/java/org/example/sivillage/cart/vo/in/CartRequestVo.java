package org.example.sivillage.cart.vo.in;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestVo {

    private String productCode;

    private Long productOptionId;

    private Integer quantity;

    public CartRequestVo(String productCode, Long productOptionId, Integer quantity) {
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.quantity = quantity;
    }
}
