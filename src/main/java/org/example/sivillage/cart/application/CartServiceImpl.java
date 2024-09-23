package org.example.sivillage.cart.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.cart.domain.Cart;
import org.example.sivillage.cart.dto.in.CartRequestDto;
import org.example.sivillage.cart.dto.out.CartAmountResponseDto;
import org.example.sivillage.cart.dto.out.CartResponseDto;
import org.example.sivillage.cart.infrastructure.CartRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Transactional(readOnly = true)
    public List<IdListResponseDto<Long>> getMemberCartIds(String memberUuid) {
        return cartRepository.findByMemberUuid(memberUuid).stream().map(IdListResponseDto::from).toList();
    }

    @Transactional(readOnly = true)
    public CartResponseDto getCartInfo(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        return CartResponseDto.from(cart);
    }

    @Transactional(readOnly = true)
    public CartAmountResponseDto getCartQuantity(String memberUuid) {
        return CartAmountResponseDto.from(cartRepository.countByMemberUuid(memberUuid));
    }

    public void addCart(CartRequestDto cartRequestDto, String memberUuid) {
        Optional<Cart> existingCartOpt = cartRepository.findByMemberUuidAndProductCodeAndProductOptionId(memberUuid, cartRequestDto.getProductCode(), cartRequestDto.getProductOptionId());

        if (existingCartOpt.isPresent()) {

            throw new BaseException(BaseResponseStatus.DUPLICATE_CART);
        }
        cartRepository.save(CartRequestDto.toEntity(cartRequestDto, memberUuid));
    }

    public void addDuplicateCart(CartRequestDto cartRequestDto, String memberUuid) {
        Optional<Cart> existingCartOpt = cartRepository.findByMemberUuidAndProductCodeAndProductOptionId(memberUuid, cartRequestDto.getProductCode(), cartRequestDto.getProductOptionId());
        cartRepository.save(cartRequestDto.updatePlusQuantity(cartRequestDto,existingCartOpt.get()));
    }

    public void changeCart(Long cartId, CartRequestDto cartRequestDto) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        cartRepository.save(cartRequestDto.updateToEntity(cartRequestDto, cart));
    }

    public void increaseQuantity(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        cartRepository.save((CartRequestDto.updateQuantity(cart,cart.getQuantity() + 1)));

    }

    public void decreaseQuantity(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        if (cart.getQuantity() > 1)
            cartRepository.save((CartRequestDto.updateQuantity(cart, cart.getQuantity() - 1)));
    }

    public void changeCartSelected(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        cart.changeSelected();
        cartRepository.save(cart);
    }

    public void removeCart(Long cartId) {
        cartRepository.findById(cartId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_CART));
        cartRepository.deleteById(cartId);
    }
}
