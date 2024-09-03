package org.example.sivillage.shippingaddress.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.shippingaddress.infrastructure.ShippingAddressRepository;
import org.example.sivillage.shippingaddress.dto.in.ShippingAddressRequestDto;
import org.example.sivillage.shippingaddress.dto.out.ShippingAddressResponseDto;
import org.example.sivillage.shippingaddress.domain.ShippingAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    // 기본 배송지 비활성화 로직
    private void deactivateExistingDefaultAddress(String memberUuid) {
        shippingAddressRepository.findByMemberUuidAndDefaultAddress(memberUuid, true)
                .ifPresent(shippingAddress -> {
                    shippingAddress.deactivateAsDefault();
                    shippingAddressRepository.save(shippingAddress);
                });
    }

    public void addDefaultShippingAddress(ShippingAddressRequestDto dto, String memberUuid) {
        if (shippingAddressRepository.findAllByMemberUuid(memberUuid).isEmpty()) {
            shippingAddressRepository.save(ShippingAddress.toEntity(dto, memberUuid));
        } else {
            deactivateExistingDefaultAddress(memberUuid);
            shippingAddressRepository.save(ShippingAddress.toEntity(dto, memberUuid)); // 처리 후 새로운 기본 배송지 등록
        } }

    public void addShippingAddress(ShippingAddressRequestDto dto, String memberUuid) {
        if (dto.isDefaultAddress()) {
            deactivateExistingDefaultAddress(memberUuid);
        }
            shippingAddressRepository.save(ShippingAddress.toEntity(dto, memberUuid));
    }

    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid) {
        return shippingAddressRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(ShippingAddressResponseDto::toDto).toList();
    }

    public void changeShippingAddress(ShippingAddressRequestDto dto, String memberUuid, Long shippingAddressId) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByMemberUuidAndShippingAddressId(memberUuid, shippingAddressId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));
        if (dto.isDefaultAddress()) {
            deactivateExistingDefaultAddress(memberUuid);
            shippingAddress.change(dto);
            shippingAddressRepository.save(shippingAddress);
        } else {
            shippingAddress.change(dto);
            shippingAddressRepository.save(shippingAddress);
        } }

    public void removeShippingAddress(String memberUuid, Long shippingAddressId){
        ShippingAddress shippingAddress = shippingAddressRepository.findByMemberUuidAndShippingAddressId(memberUuid, shippingAddressId)
               .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));
        shippingAddressRepository.delete(shippingAddress);
    }


}

