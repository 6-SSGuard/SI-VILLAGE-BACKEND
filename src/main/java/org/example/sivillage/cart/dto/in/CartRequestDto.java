package org.example.sivillage.cart.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.cart.domain.Cart;

@Getter
public class CartRequestDto {

    private String productCode;

    private String productOption;

    private Integer amount;


    public static Cart toEntity(CartRequestDto cartRequestDto, String memberUuid) {
        return Cart.builder()
                .memberUuid(memberUuid)
                .productCode(cartRequestDto.getProductCode())
                .productOption(cartRequestDto.getProductOption())
                .amount(cartRequestDto.getAmount())
                .selected(true)
                .build();
    }

    public Cart updateAmount(CartRequestDto cartRequestDto, Cart cart){
        return Cart.builder()
                .id(cart.getId())
                .memberUuid(cart.getMemberUuid())
                .productCode(cart.getProductCode())
                .productOption(cart.getProductOption())
                .amount(cart.getAmount() + cartRequestDto.getAmount())
                .selected(true)
                .build();
    }

    public Cart updateToEntity(CartRequestDto cartRequestDto, Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .memberUuid(cart.getMemberUuid())
                .productCode(cart.getProductCode())
                .productOption(cartRequestDto.getProductOption())
                .amount(cartRequestDto.getAmount())
                .selected(true)
                .build();
    }

    @Builder
    public CartRequestDto(String productCode, String productOption, Integer amount) {
        this.productCode = productCode;
        this.productOption = productOption;
        this.amount = amount;
    }

}
