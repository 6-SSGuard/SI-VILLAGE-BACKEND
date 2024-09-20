package org.example.sivillage.cart.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.vo.out.CartResponseVo;

@Getter
public class CartResponseDto {

    private Long cartId;

    private String productCode;

    private String productOption;

    private Integer amount;

    private boolean selected;

    public static CartResponseDto from(Cart cart){
        return CartResponseDto.builder()
                .cartId(cart.getId())
               .productCode(cart.getProductCode())
                .productOption(cart.getProductOption())
                .amount(cart.getAmount())
                .selected(cart.isSelected())
                .build();
    }

    public CartResponseVo toResponseVo(){
    return CartResponseVo.builder()
                .cartId(cartId)
               .productCode(productCode)
               .productOption(productOption)
               .amount(amount)
               .selected(selected)
               .build();
}

@Builder
public CartResponseDto(Long cartId, String productCode, String productOption, Integer amount, boolean selected) {
    this.cartId = cartId;
    this.productCode = productCode;
    this.productOption = productOption;
    this.amount = amount;
    this.selected = selected;}
}