package org.example.sivillage.cart.vo.out;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseVo {

    private Long cartId;

    private String productCode;

    private Long productOptionId;

    private Integer quantity;

    private boolean selected;


}
