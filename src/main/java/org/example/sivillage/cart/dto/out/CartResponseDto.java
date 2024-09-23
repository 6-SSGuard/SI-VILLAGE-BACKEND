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

    private Integer quantity;

    private boolean selected;

    public static CartResponseDto from(Cart cart){
        return CartResponseDto.builder()
                .cartId(cart.getId())
                .productCode(cart.getProductCode())
                .productOptionId(cart.getProductOptionId())
                .quantity(cart.getQuantity())
                .selected(cart.isSelected())
                .build();
    }

    public CartResponseVo toResponseVo(){
    return CartResponseVo.builder()
                .cartId(cartId)
               .productCode(productCode)
               .productOptionId(productOptionId)
               .quantity(quantity)
               .selected(selected)
               .build();
}

@Builder
public CartResponseDto(Long cartId, String productCode, Long productOptionId, Integer quantity, boolean selected) {
    this.cartId = cartId;
    this.productCode = productCode;
    this.productOptionId = productOptionId;
    this.quantity = quantity;
    this.selected = selected;}
}