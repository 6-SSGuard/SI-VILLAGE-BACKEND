package org.example.sivillage.brand.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBrandsResponseDto {
    private Long brandId;
    private String brandEngName;
    private String brandKorName;
    private boolean isLiked;
}
