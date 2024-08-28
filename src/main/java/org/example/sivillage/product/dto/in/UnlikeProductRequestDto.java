package org.example.sivillage.product.dto.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnlikeProductRequestDto {
    private String productCode;
    private String memberUuid;
}
