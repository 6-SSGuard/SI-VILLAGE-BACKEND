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

    private Integer amount;

    @Builder
    public CartRequestDto(String productCode, Long productOptionId, Integer amount) {
        this.productCode = productCode;
        this.productOptionId = productOptionId;
        this.amount = amount;
    }

    public static CartRequestDto from(CartRequestVo cartRequestVo) {
        return CartRequestDto.builder()
                .productCode(cartRequestVo.getProductCode())
                .productOptionId(cartRequestVo.getProductOptionId())
                .amount(cartRequestVo.getAmount())
                .build();
    }


    public static Cart toEntity(CartRequestDto cartRequestDto, String memberUuid) {
        return Cart.builder()
                .memberUuid(memberUuid)
                .productCode(cartRequestDto.getProductCode())
                .productOptionId(cartRequestDto.getProductOptionId())
                .amount(cartRequestDto.getAmount())
                .selected(true)
                .build();
    }

    public Cart updateAmount(CartRequestDto cartRequestDto, Cart cart){
        return Cart.builder()
                .id(cart.getId())
                .memberUuid(cart.getMemberUuid())
                .productCode(cart.getProductCode())
                .productOptionId(cart.getProductOptionId())
                .amount(cart.getAmount() + cartRequestDto.getAmount())
                .selected(true)
                .build();
    }

    public Cart updateToEntity(CartRequestDto cartRequestDto, Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .memberUuid(cart.getMemberUuid())
                .productCode(cart.getProductCode())
                .productOptionId(cartRequestDto.getProductOptionId())
                .amount(cartRequestDto.getAmount())
                .selected(true)
                .build();
    }

}
