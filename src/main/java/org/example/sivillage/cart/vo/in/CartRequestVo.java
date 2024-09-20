package org.example.sivillage.cart.vo.in;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.cart.dto.in.CartRequestDto;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestVo {

    @Schema(description = "상품코드", example = "productUuid", required = true)
    @NotNull
    private String productCode;

    @Schema(description = "상품옵션", example = "50ml", required = true)
    @NotNull
    private String productOption;

    @Schema(description = "수량", example = "2", required = true)
    @NotNull
    private Integer amount;

    public static CartRequestDto toDto(CartRequestVo cartRequestVo) {
        return CartRequestDto.builder()
               .productCode(cartRequestVo.getProductCode())
               .productOption(cartRequestVo.getProductOption())
               .amount(cartRequestVo.getAmount())
               .build();
    }
}
