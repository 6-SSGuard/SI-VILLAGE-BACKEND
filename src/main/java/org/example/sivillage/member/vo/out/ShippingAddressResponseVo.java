package org.example.sivillage.member.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.dto.out.ShippingAddressResponseDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ShippingAddressResponseVo {

    private Long shippingAddressId;

    private String addressName;

    private String recipient;

    private String phone;

    private String address;

    private String detailedAddress;

    private String postalCode;

    private boolean defaultAddress;

    public static ShippingAddressResponseVo toVo(ShippingAddressResponseDto dto) {
        return ShippingAddressResponseVo.builder()
                .shippingAddressId(dto.getShippingAddressId())
                .addressName(dto.getAddressName())
                .recipient(dto.getRecipient())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .detailedAddress(dto.getDetailedAddress())
                .postalCode(dto.getPostalCode())
                .defaultAddress(dto.isDefaultAddress())
                .build();
    }

    }

