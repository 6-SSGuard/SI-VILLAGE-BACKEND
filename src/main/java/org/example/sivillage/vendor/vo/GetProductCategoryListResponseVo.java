package org.example.sivillage.vendor.vo;

import lombok.*;
import org.example.sivillage.vendor.dto.out.GetProductCategoryResponseDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductCategoryListResponseVo {
    private List<GetProductCategoryResponseDto> productCodes;
}
