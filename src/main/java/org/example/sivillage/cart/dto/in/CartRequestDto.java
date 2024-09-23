package org.example.sivillage.cart.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.vo.in.CartRequestVo;

@Getter
@NoArgsConstructor
public class CartRequestDto {

    private String productCode;

    private Long productOptionId;

    private Integer quantity;

    @Builder
    public CartRequestDto(String productCode, Long productOptionId, Integer quantity) {
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.quantity = quantity;
    }

    public static CartRequestDto from(CartRequestVo cartRequestVo) {
        return CartRequestDto.builder()
                .productCode(cartRequestVo.getProductCode())
                .productOptionId(cartRequestVo.getProductOptionId())
                .quantity(cartRequestVo.getQuantity())
                .build();
    }


    public static Cart toEntity(CartRequestDto cartRequestDto, String memberUuid) {
        return Cart.builder()
                .memberUuid(memberUuid)
                .productCode(cartRequestDto.getProductCode())
                .productOptionId(cartRequestDto.getProductOptionId())
                .quantity(cartRequestDto.getQuantity())
                .selected(true)
                .build();
    }

    public Cart updatePlusQuantity(CartRequestDto cartRequestDto, Cart cart){
        return Cart.builder()
                .id(cart.getId())
                .memberUuid(cart.getMemberUuid())
                .productCode(cart.getProductCode())
                .productOptionId(cart.getProductOptionId())
                .quantity(cart.getQuantity() + cartRequestDto.getQuantity())
                .selected(true)
                .build();
    }

    public static Cart updateQuantity(Cart cart, Integer quantity){
        return Cart.builder()
                .id(cart.getId())
                .memberUuid(cart.getMemberUuid())
                .productCode(cart.getProductCode())
                .productOptionId(cart.getProductOptionId())
                .quantity(quantity)
                .selected(true)
                .build();
    }

    public Cart updateToEntity(CartRequestDto cartRequestDto, Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .memberUuid(cart.getMemberUuid())
                .productCode(cart.getProductCode())
                .productOptionId(cartRequestDto.getProductOptionId())
                .quantity(cartRequestDto.getQuantity())
                .selected(true)
                .build();
    }

}
