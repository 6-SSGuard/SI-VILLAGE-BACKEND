package org.example.sivillage.shippingaddress.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.shippingaddress.application.ShippingAddressServiceImpl;
import org.example.sivillage.shippingaddress.dto.out.ShippingAddressResponseDto;
import org.example.sivillage.shippingaddress.vo.in.ShippingAddressRequestVo;
import org.example.sivillage.shippingaddress.vo.out.ShippingAddressResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "배송지 관리 API", description = "배송지 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/shipping-addresses")
public class ShippingAddressController {

    private final ShippingAddressServiceImpl shippingAddressService;

    @Operation(summary = "배송지 등록", description = "기본 배송지를 등록합니다.")
    @PostMapping("")
    public BaseResponse<Void> addShippingAddress (@Valid @RequestBody ShippingAddressRequestVo vo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        shippingAddressService.addShippingAddress(ShippingAddressRequestVo.toDto(vo), authUserDetails.getMemberUuid()); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary ="배송지 목록 조회", description = "배송지 목록을 조회합니다.")
    @GetMapping()
    public BaseResponse<List<ShippingAddressResponseVo>> getShippingAddresses(@AuthenticationPrincipal AuthUserDetails authUserDetails){
        List<ShippingAddressResponseVo> shippingAddressResponseVoList = shippingAddressService.getShippingAddress(authUserDetails.getMemberUuid())
                .stream()
                .map(ShippingAddressResponseDto::toResponseVo).toList();
        return new BaseResponse<>(shippingAddressResponseVoList);
    }

    @Operation(summary = "배송지 수정", description = "배송지 정보를 수정합니다.")
    @PutMapping("/{shippingAddressId}")
    public BaseResponse<Void> changeShippingAddress(@PathVariable("shippingAddressId") Long shippingAddressId, @Valid @RequestBody ShippingAddressRequestVo vo, @AuthenticationPrincipal AuthUserDetails authUserDetails){
        shippingAddressService.changeShippingAddress(ShippingAddressRequestVo.toDto(vo),shippingAddressId,authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "배송지 삭제", description = "배송지 정보를 삭제합니다.")
    @DeleteMapping("/{shippingAddressId}")
    public BaseResponse<Void> removeShippingAddress(@PathVariable("shippingAddressId") Long shippingAddressId, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        shippingAddressService.removeShippingAddress(shippingAddressId,authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }


}
