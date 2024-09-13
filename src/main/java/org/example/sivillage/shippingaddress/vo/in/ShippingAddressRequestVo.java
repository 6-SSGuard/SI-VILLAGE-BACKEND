package org.example.sivillage.shippingaddress.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.shippingaddress.dto.in.ShippingAddressRequestDto;

@NoArgsConstructor
@Getter
public class ShippingAddressRequestVo {

    @Schema(description = "배송지명", example = "집", required = true)
    @NotNull
    private String addressName;

    @Schema(description = "수령인", example = "강수빈", required = true)
    @NotNull
    private String recipient;

    @Schema(description = "전화번호", example = "12345678", required = true)
    @NotNull
    private String phone;

    @Schema(description = "주소", example = "스파로스", required = true)
    @NotNull
    private String address;

    @Schema(description = "상세주소", example = "4층", required = true)
    @NotNull
    private String detailedAddress;

    @Schema(description = "우편번호", example = "000000", required = true)
    @NotNull
    private String postalCode;

    @Schema(description = "기본 배송지", example = "true", required = true)
    @NotNull
    private boolean defaultAddress;

    public static ShippingAddressRequestDto toDto (ShippingAddressRequestVo shippingAddressRequestVo) {
        return ShippingAddressRequestDto.builder()
                .addressName(shippingAddressRequestVo.getAddressName())
                .recipient(shippingAddressRequestVo.getRecipient())
                .phone(shippingAddressRequestVo.getPhone())
                .address(shippingAddressRequestVo.getAddress())
                .detailedAddress(shippingAddressRequestVo.getDetailedAddress())
                .postalCode(shippingAddressRequestVo.getPostalCode())
                .defaultAddress(shippingAddressRequestVo.isDefaultAddress())
                .build();
    }

}
