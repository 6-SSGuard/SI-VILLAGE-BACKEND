package org.example.sivillage.vendor.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductCategoryListResponseDto {
    private List<String> productCodeList;

}
