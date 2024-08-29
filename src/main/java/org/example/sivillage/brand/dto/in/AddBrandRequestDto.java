package org.example.sivillage.brand.dto.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddBrandRequestDto {
    private String brandEngName;
    private String brandKorName;
}
