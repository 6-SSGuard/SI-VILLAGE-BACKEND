package org.example.sivillage.brand.vo.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddBrandRequestVo {
    @NotNull(message = "Brand English Name cannot be null")
    @NotEmpty(message = "Brand English Name cannot be empty")
    private String brandEngName;
    @NotNull(message = "Brand Korean Name cannot be null")
    @NotEmpty(message = "Brand Korean Name cannot be empty")
    private String brandKorName;
}
