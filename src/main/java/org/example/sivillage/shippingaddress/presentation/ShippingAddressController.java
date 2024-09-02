package org.example.sivillage.shippingaddress.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.CustomUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.shippingaddress.application.ShippingAddressService;
import org.example.sivillage.shippingaddress.vo.in.ShippingAddressRequestVo;
import org.example.sivillage.shippingaddress.vo.out.ShippingAddressResponseVo;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "배송지 관리 API", description = "배송지 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/shipping-addresses")
public class ShippingAddressController {

    //todo: 배송지 삭제 시 기본 배송지는 삭제 하지 못하게 수정
    //todo: 기본 배송지 등록은 무조건 기본 배송지 true 로 설정

    private final ShippingAddressService shippingAddressService;
    private final ModelMapper mapper;

    @Operation(summary ="기본 배송지 등록", description = "기본 배송지를 등록합니다.")
    @PostMapping("/default")
    public BaseResponse<Void> addDefaultShippingAddress (@Valid @RequestBody ShippingAddressRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        shippingAddressService.addDefaultShippingAddress(ShippingAddressRequestVo.toDto(vo),customUserDetails.getMemberUuid()); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary ="추가 배송지 등록", description = "배송지를 등록합니다.")
    @PostMapping("")
    public BaseResponse<Void> addShippingAddress (@Valid @RequestBody ShippingAddressRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        shippingAddressService.addShippingAddress(ShippingAddressRequestVo.toDto(vo),customUserDetails.getMemberUuid()); // vo -> dto
        return new BaseResponse<>();
    }


    @Operation(summary ="배송지 목록 조회", description = "배송지 목록을 조회합니다.")
    @GetMapping()
    public BaseResponse<List<ShippingAddressResponseVo>> getShippingAddresses(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        List<ShippingAddressResponseVo> vo = shippingAddressService.getShippingAddress(customUserDetails.getMemberUuid())
                .stream()
                .map(ShippingAddressResponseVo::toVo).toList();
        return new BaseResponse<>(vo);
    }

    @Operation(summary ="배송지 수정", description ="배송지 정보를 수정합니다.")
    @PutMapping("/{shippingAddressId}")
    public BaseResponse<Void> changeShippingAddress(@PathVariable("shippingAddressId") Long shippingAddressId, @Valid @RequestBody ShippingAddressRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        shippingAddressService.changeShippingAddress(ShippingAddressRequestVo.toDto(vo), customUserDetails.getMemberUuid(), shippingAddressId);
        return new BaseResponse<>();
    }

    @Operation(summary = "배송지 삭제", description = "배송지 정보를 삭제합니다.")
    @DeleteMapping("/{shippingAddressId}")
    public BaseResponse<Void> removeShippingAddress(@PathVariable("shippingAddressId") Long shippingAddressId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        shippingAddressService.removeShippingAddress(customUserDetails.getMemberUuid(),shippingAddressId);
        return new BaseResponse<>();
    }


}
