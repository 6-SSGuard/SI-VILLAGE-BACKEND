package org.example.sivillage.vendor.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductCategoryResponseDto {
    private String productCode;

    public static GetProductCategoryResponseDto toDto(String productCode) {
        return new GetProductCategoryResponseDto(productCode);
    }
}
