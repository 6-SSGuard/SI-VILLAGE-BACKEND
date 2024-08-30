package org.example.sivillage.admin.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddBottomCategoryRequestVo {
    private String middleCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;
}
