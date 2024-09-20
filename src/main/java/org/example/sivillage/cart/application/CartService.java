package org.example.sivillage.cart.application;

import org.example.sivillage.cart.dto.in.CartRequestDto;
import org.example.sivillage.cart.dto.out.CartResponseDto;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;

import java.util.List;

public interface CartService {

    List<IdListResponseDto<Long>> getMemberCartIds(String memberUuid);
    CartResponseDto getCartInfo(Long cartId);
    void addCart(CartRequestDto cartRequestDto, String memberUuid);
    void changeCart(Long cartId, CartRequestDto cartRequestDto);
    void changeCartSelected(Long cartId);
    void removeCart(Long cartId);

}
