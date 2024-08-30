package org.example.sivillage.member.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.domain.ShippingAddress;
import org.example.sivillage.member.dto.in.ShippingAddressRequestDto;
import org.example.sivillage.member.dto.out.ShippingAddressResponseDto;
import org.example.sivillage.member.infrastructure.ShippingAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    public void addShippingAddress(ShippingAddressRequestDto dto, String memberUuid) {
        if (shippingAddressRepository.findAllByMemberUuid(memberUuid).isEmpty()) {
            shippingAddressRepository.save(ShippingAddress.toEntity(dto, memberUuid));
        } else {
            shippingAddressRepository.findByMemberUuidAndDefaultAddress(memberUuid, true)
                    .ifPresent(shippingAddress -> {
                        shippingAddress.deactivateAsDefault();// true 값이 들어 왔기 때문에 기존의 기본 배송지는 false 로 변경
                        shippingAddressRepository.save(ShippingAddress.toEntity(dto, memberUuid));
                    });
        }
    }

    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid) {
        return shippingAddressRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(ShippingAddressResponseDto::toDto).toList();
    }

    public void changeShippingAddress(ShippingAddressRequestDto dto, String memberUuid, Long shippingAddressId){
        ShippingAddress shippingAddress = shippingAddressRepository.findByMemberUuidAndShippingAddressId(memberUuid, shippingAddressId)
              .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));
      shippingAddress.change(dto);
      shippingAddressRepository.save(shippingAddress);
    }

    public void removeShippingAddress(String memberUuid, Long shippingAddressId){
        ShippingAddress shippingAddress = shippingAddressRepository.findByMemberUuidAndShippingAddressId(memberUuid, shippingAddressId)
               .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));
        shippingAddressRepository.delete(shippingAddress);
    }



}

