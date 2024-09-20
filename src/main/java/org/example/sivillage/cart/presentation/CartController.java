package org.example.sivillage.cart.presentation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.cart.application.CartServiceImpl;
import org.example.sivillage.cart.dto.out.CartResponseDto;
import org.example.sivillage.cart.vo.in.CartRequestVo;
import org.example.sivillage.cart.vo.out.CartResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "장바구니 관리 API", description = "장바구니 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartServiceImpl cartService;

    @Operation(summary = "회원의 장바구니 id 조회", description = "회원의 장바구니 id 리스트를 반환")
    @GetMapping("")
    public BaseResponse<List<IdListResponseVo<Long>>> getMemberCartIds(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        List<IdListResponseVo<Long>> idListResponseVoList = cartService.getMemberCartIds(authUserDetails.getMemberUuid())
                .stream().map(IdListResponseDto::toVo).toList();
        return new BaseResponse<>(idListResponseVoList);
    }

    @Operation(summary = "장바구니 조회", description = "장바구니 상세내용을 조회합니다.")
    @GetMapping("/{cartId}")
    public BaseResponse<CartResponseVo> getCartInfo(@PathVariable Long cartId) {
        CartResponseDto cartResponseDto = cartService.getCartInfo(cartId);
        return new BaseResponse<>(cartResponseDto.toResponseVo());
    }

    @Operation(summary = "장바구니 추가", description = "장바구니에 상품을 추가합니다.")
    @PostMapping("")
    public BaseResponse<Void> addCart(@RequestBody CartRequestVo cartRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        cartService.addCart(CartRequestVo.toDto(cartRequestVo), authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "장바구니 수정", description = "장바구니에 해당 상품 옵션을 수정합니다.")
    @PutMapping("/{cartId}")
    public BaseResponse<Void> changeCart(@PathVariable Long cartId, @RequestBody CartRequestVo cartRequestVo) {
        cartService.changeCart(cartId, CartRequestVo.toDto(cartRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "장바구니 토글", description = "장바구니 체크박스 토글")
    @PutMapping("/{cartId}/toggle")
    public BaseResponse<Void> changeCartSelected(@PathVariable Long cartId) {
        cartService.changeCartSelected(cartId);
        return new BaseResponse<>();
    }

    @Operation(summary = "장바구니 삭제", description = "장바구니에 해당 상품을 삭제합니다.")
    @DeleteMapping("/{cartId}")
    public BaseResponse<Void> removeCart(@PathVariable Long cartId) {
        cartService.removeCart(cartId);
        return new BaseResponse<>();
    }
}
