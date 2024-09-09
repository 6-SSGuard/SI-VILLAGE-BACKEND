package org.example.sivillage.product.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductFromCsvRequestDto {
    private String productName;
    private String productCode;
    private Integer price;
    private String detailContent;
    private Long brandId;
    private String categoryCode;
}