package org.example.sivillage.cart.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.cart.dto.CartQuantityRequestDto;

@Getter
@NoArgsConstructor
public class CartQuantityRequestVo {

    private Integer quantity;

    public static CartQuantityRequestDto toDto(CartQuantityRequestVo cartQuantityRequestVo){
        return CartQuantityRequestDto.builder()
                .quantity(cartQuantityRequestVo.getQuantity())
                .build();
    }


}
