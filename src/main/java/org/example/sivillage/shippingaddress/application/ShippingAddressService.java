package org.example.sivillage.shippingaddress.application;

import org.example.sivillage.shippingaddress.dto.in.ShippingAddressRequestDto;
import org.example.sivillage.shippingaddress.dto.out.ShippingAddressResponseDto;

import java.util.List;

public interface ShippingAddressService {
    // 배송지 등록(기본 배송지로 설정시 기존 기본 배송지는 변경 됨)
    public void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid);
    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid);
    // 배송지 수정
//    public void changeShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, Long shippingAddressId);
//    public void removeShippingAddress(Long shippingAddressId);
//   // 배송지 삭제

    
}
