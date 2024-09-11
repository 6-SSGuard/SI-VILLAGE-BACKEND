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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    //  첫 등록 시 사용
    public void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid) {
        Optional<ShippingAddress> existingAddress = shippingAddressRepository.findByMemberUuid(memberUuid);
        if (existingAddress.isEmpty()) {
            shippingAddressRepository.save(shippingAddressRequestDto.toEntity(shippingAddressRequestDto, memberUuid));
        } else {
            throw new BaseException(BaseResponseStatus.DuPLICATE_ADDRESS);
        }
    }

    // 조회
    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid) {
        return shippingAddressRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(ShippingAddressResponseDto::from).toList();
    }

    // 수정
    public void changeShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, Long shippingAddressId, String memberUuid) {

        if (shippingAddressRequestDto.isDefaultAddress()) {
            deactivateExistingDefaultAddress(shippingAddressRequestDto, memberUuid);
        } else {
            shippingAddressRepository.save(shippingAddressRequestDto.toEntity(shippingAddressRequestDto, memberUuid));
        }
    }
    //삭제
    public void removeShippingAddress(Long shippingAddressId, String memberUuid){
        ShippingAddress shippingAddress = shippingAddressRepository.findByMemberUuidAndId(memberUuid, shippingAddressId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));
        shippingAddressRepository.delete(shippingAddress);
    }

    // 기본 배송지 비활성화 로직
    private void deactivateExistingDefaultAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid) {
        shippingAddressRepository.findByMemberUuidAndDefaultAddress(memberUuid, true)
                .ifPresent(shippingAddress -> {
                   // 기존 배송지 변경
                    shippingAddressRepository.save(shippingAddressRequestDto.deactivateDefaultAddress(shippingAddress));
                });
    }

}