package org.example.sivillage.cart.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.dto.in.CartRequestDto;
import org.example.sivillage.cart.dto.out.CartResponseDto;
import org.example.sivillage.cart.infrastructure.CartRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public List<IdListResponseDto<Long>> getMemberCartIds(String memberUuid) {
        return cartRepository.findByMemberUuid(memberUuid)
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    public CartResponseDto getCartInfo(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        return CartResponseDto.from(cart);
    }

    public void addCart(CartRequestDto cartRequestDto, String memberUuid) {
        cartRepository.save(CartRequestDto.toEntity(cartRequestDto, memberUuid));
    }

    public void changeCart(Long cartId, CartRequestDto cartRequestDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        cartRepository.save(cartRequestDto.updateToEntity(cartRequestDto, cart));
    }
    

    public void changeCartSelected(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        cart.changeSelected();
        cartRepository.save(cart);
    }

    public void removeCart(Long cartId) {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        cartRepository.deleteById(cartId);
    }
}
