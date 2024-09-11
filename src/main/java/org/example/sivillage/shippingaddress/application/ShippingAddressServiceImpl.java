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
    // 등록

    // 수정


    @Override
    public void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid) {

    }

    // 조회
    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid) {
        return shippingAddressRepository.findAllByMemberUuid(memberUuid)
                .stream()
                .map(ShippingAddressResponseDto::from).toList();
    }

    @Override
    public void changeShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, Long shippingAddressId, String memberUuid) {

    }


    //삭제
    public void removeShippingAddress(Long shippingAddressId, String memberUuid) {
        ShippingAddress shippingAddress = shippingAddressRepository.findByMemberUuidAndId(memberUuid, shippingAddressId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SHIPPING_ADDRESS));
        shippingAddressRepository.delete(shippingAddress);
    }
}