package org.example.sivillage.shippingaddress.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.shippingaddress.domain.ShippingAddress;
import org.example.sivillage.shippingaddress.vo.out.ShippingAddressResponseVo;

@Getter
public class ShippingAddressResponseDto {
    private Long shippingAddressId;

    private String addressName;

    private String recipient;

    private String phone;

    private String address;

    private String detailedAddress;

    private String postalCode;

    private boolean defaultAddress;

    public static ShippingAddressResponseDto from(ShippingAddress shippingAddress) {
        return ShippingAddressResponseDto.builder()
                .shippingAddressId(shippingAddress.getId())
                .addressName(shippingAddress.getAddressName())
                .recipient(shippingAddress.getRecipient())
                .phone(shippingAddress.getPhone())
                .address(shippingAddress.getAddress())
                .detailedAddress(shippingAddress.getDetailedAddress())
                .postalCode(shippingAddress.getPostalCode())
                .defaultAddress(shippingAddress.isDefaultAddress())
                .build();
    }

    public ShippingAddressResponseVo toResponseVo() {
        return ShippingAddressResponseVo.builder()
                .shippingAddressId(shippingAddressId)
                .addressName(addressName)
                .recipient(recipient)
                .phone(phone)
                .address(address)
                .detailedAddress(detailedAddress)
                .postalCode(postalCode)
                .defaultAddress(defaultAddress)
                .build();
    }



    @Builder
    public ShippingAddressResponseDto(Long shippingAddressId, String addressName, String recipient, String phone, String address, String detailedAddress, String postalCode, boolean defaultAddress) {
        this.shippingAddressId = shippingAddressId;
        this.addressName = addressName;
        this.recipient = recipient;
        this.phone = phone;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.postalCode = postalCode;
        this.defaultAddress = defaultAddress;
    }

}
