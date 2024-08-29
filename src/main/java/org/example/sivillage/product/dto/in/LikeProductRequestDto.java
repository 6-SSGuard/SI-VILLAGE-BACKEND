package org.example.sivillage.product.dto.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeProductRequestDto {
    private String productCode;
    private String memberUuid;
}
