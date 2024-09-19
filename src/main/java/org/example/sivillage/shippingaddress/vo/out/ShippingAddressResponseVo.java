package org.example.sivillage.shippingaddress.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.shippingaddress.dto.out.ShippingAddressResponseDto;

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


    }

