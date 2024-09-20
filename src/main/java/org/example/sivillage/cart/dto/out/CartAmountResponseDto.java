package org.example.sivillage.cart.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.vo.out.CartAmountResponseVo;

@Getter
public class CartAmountResponseDto {

    private Integer amount;

    public static CartAmountResponseDto from(Integer amount) {
        return CartAmountResponseDto.builder()
                .amount(amount)
                .build();
    }

    public CartAmountResponseVo toResponseVo(){
        return CartAmountResponseVo.builder()
                .amount(amount)
                .build();
    }

    @Builder
    public CartAmountResponseDto(Integer amount) {
        this.amount = amount;
    }
}
