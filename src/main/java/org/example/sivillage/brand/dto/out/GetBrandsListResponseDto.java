package org.example.sivillage.brand.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBrandsListResponseDto {
    private Long brandId;
    private String brandEngName;
    private String brandKorName;
    private boolean isLiked;
}
