package org.example.sivillage.cart.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.cart.vo.out.CartAmountResponseVo;

@Getter
public class CartAmountResponseDto {

    private Integer quantity;

    public static CartAmountResponseDto from(Integer quantity) {
        return CartAmountResponseDto.builder()
                .quantity(quantity)
                .build();
    }

    public CartAmountResponseVo toResponseVo(){
        return CartAmountResponseVo.builder()
                .quantity(quantity)
                .build();
    }

    @Builder
    public CartAmountResponseDto(Integer quantity) {
        this.quantity = quantity;
    }
}
