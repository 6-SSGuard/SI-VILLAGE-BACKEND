package org.example.sivillage.admin.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddMiddleCategoryRequestVo {
    private String topCategoryCode;
    private String middleCategoryName;
    private String middleCategoryDescription;
}
