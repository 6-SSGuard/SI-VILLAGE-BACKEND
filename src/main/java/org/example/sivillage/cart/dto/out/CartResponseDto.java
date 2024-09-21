package org.example.sivillage.cart.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.vo.out.CartResponseVo;

@Getter
public class CartResponseDto {

    private Long cartId;

    private String productCode;

    private Long productOptionId;

    private Integer amount;

    private boolean selected;

    public static CartResponseDto from(Cart cart){
        return CartResponseDto.builder()
                .cartId(cart.getId())
                .productCode(cart.getProductCode())
                .productOptionId(cart.getProductOptionId())
                .amount(cart.getAmount())
                .selected(cart.isSelected())
                .build();
    }

    public CartResponseVo toResponseVo(){
    return CartResponseVo.builder()
                .cartId(cartId)
               .productCode(productCode)
               .productOptionId(productOptionId)
               .amount(amount)
               .selected(selected)
               .build();
}

@Builder
public CartResponseDto(Long cartId, String productCode, Long productOptionId, Integer amount, boolean selected) {
    this.cartId = cartId;
    this.productCode = productCode;
    this.productOptionId = productOptionId;
    this.amount = amount;
    this.selected = selected;}
}