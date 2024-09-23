package org.example.sivillage.shippingaddress.application;

import org.example.sivillage.shippingaddress.dto.in.ShippingAddressRequestDto;
import org.example.sivillage.shippingaddress.dto.out.ShippingAddressResponseDto;

import java.util.List;

public interface ShippingAddressService {
    public void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDto, String memberUuid);
    public List<ShippingAddressResponseDto> getShippingAddress(String memberUuid);
    public void changeToDefaultShippingAddress(Long shippingAddressId, String memberUuid);
    public void removeShippingAddress(Long shippingAddressId, String memberUuid);
    
}
