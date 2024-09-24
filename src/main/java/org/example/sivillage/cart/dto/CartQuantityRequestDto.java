package org.example.sivillage.cart.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartQuantityRequestDto {

    private Integer quantity;

    @Builder
    public CartQuantityRequestDto(Integer quantity) {
        this.quantity = quantity;
    }

}
