package org.example.sivillage.shippingaddress.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.shippingaddress.infrastructure.ShippingAddressRepository;
import org.example.sivillage.shippingaddress.dto.in.ShippingAddressRequestDto;
import org.example.sivillage.shippingaddress.dto.out.ShippingAddressResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    // 기본배송지로 등록하면 기존 배송지 변경됨
    public void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid) {
        if (shippingAddressRequestDto.isDefaultAddress()) {
        shippingAddressRepository.findByMemberUuidAndDefaultAddress(memberUuid, true)
                        .ifPresent(existingDefaultAddress -> {
                            shippingAddressRepository.save(shippingAddressRequestDto.updateDefaultAddress()); // 기존 기본 배송지 변경~
                        }); }

        shippingAddressRepository.save(shippingAddressRequestDto.toEntity(shippingAddressRequestDto, memberUuid));
    }


    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid) {
        return shippingAddressRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(ShippingAddressResponseDto::from).toList();
    }


}

