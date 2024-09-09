package org.example.sivillage.vendor.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductCategoryListResponseVo {
    private List<String> productCodeList;
}
