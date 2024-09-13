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
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    public void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid) {
        shippingAddressRepository.findByMemberUuidAndDefaultAddress(memberUuid, shippingAddressRequestDto.isDefaultAddress())
                .ifPresent(address -> shippingAddressRepository.save(shippingAddressRequestDto.deactivateDefaultAddress(address)));
        shippingAddressRepository.save(shippingAddressRequestDto.toEntity(shippingAddressRequestDto, memberUuid));
    }

    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid) {
        return shippingAddressRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(ShippingAddressResponseDto::from).toList();
    }

    public void changeShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid, Long shippingAddressId) {

        ShippingAddress shippingAddress = shippingAddressRepository.findById(shippingAddressId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));

        if (shippingAddressRequestDto.isDefaultAddress()) {
            shippingAddressRepository.findByMemberUuidAndDefaultAddress(memberUuid, true)
                    .ifPresent(existingDefaultAddress ->
                            shippingAddressRepository.save(shippingAddressRequestDto.deactivateDefaultAddress(existingDefaultAddress)));
        }
        shippingAddressRepository.save(shippingAddressRequestDto.updateToEntity(shippingAddressRequestDto, shippingAddress));
    }

    public void removeShippingAddress(Long shippingAddressId, String memberUuid) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByMemberUuidAndId(memberUuid, shippingAddressId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));
        shippingAddressRepository.delete(shippingAddress);
    }
}
