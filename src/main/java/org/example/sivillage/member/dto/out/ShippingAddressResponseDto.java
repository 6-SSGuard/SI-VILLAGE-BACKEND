package org.example.sivillage.member.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.domain.ShippingAddress;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressResponseDto {
    private Long shippingAddressId;

    private String addressName;

    private String recipient;

    private String phone;

    private String address;

    private String detailedAddress;

    private String postalCode;

    private boolean defaultAddress;

    public static ShippingAddressResponseDto toDto (ShippingAddress shippingAddress) {
        return ShippingAddressResponseDto.builder()
                .shippingAddressId(shippingAddress.getShippingAddressId())
                .addressName(shippingAddress.getAddressName())
                .recipient(shippingAddress.getRecipient())
                .phone(shippingAddress.getPhone())
                .address(shippingAddress.getAddress())
                .detailedAddress(shippingAddress.getDetailedAddress())
                .postalCode(shippingAddress.getPostalCode())
                .defaultAddress(shippingAddress.isDefaultAddress())
                .build();
    }
}
