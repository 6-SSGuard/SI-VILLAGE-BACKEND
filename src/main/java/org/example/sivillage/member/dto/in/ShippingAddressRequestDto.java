package org.example.sivillage.member.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressRequestDto {

    private String addressName;

    private String recipient;

    private String phone;

    private String address;

    private String detailedAddress;

    private String postalCode;

    private boolean defaultAddress;

}
