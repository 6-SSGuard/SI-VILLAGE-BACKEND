package org.example.sivillage.shippingaddress.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.shippingaddress.dto.in.ShippingAddressRequestDto;

@Getter
@NoArgsConstructor
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
    // 주소 검색 api 는 프론트에서 연결해서 값을 넘겨주면 그 값을 db에 저장하면 되는건가?

    @Schema(description = "상세주소", example = "4층", required = true)
    @NotNull
    private String detailedAddress;

    @Schema(description = "우편번호", example = "000000", required = true)
    @NotNull
    private String postalCode;

    @Schema(description = "기본 배송지", example = "true", required = true)
    @NotNull
    private boolean defaultAddress;

    public static ShippingAddressRequestDto toDto (ShippingAddressRequestVo vo) {
        return ShippingAddressRequestDto.builder()
                .addressName(vo.getAddressName())
                .recipient(vo.getRecipient())
                .phone(vo.getPhone())
                .address(vo.getAddress())
                .detailedAddress(vo.getDetailedAddress())
                .postalCode(vo.getPostalCode())
                .defaultAddress(vo.isDefaultAddress())
                .build();
    }
}
