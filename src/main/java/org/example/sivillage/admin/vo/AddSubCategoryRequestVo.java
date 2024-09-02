package org.example.sivillage.admin.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddSubCategoryRequestVo {
    private String bottomCategoryCode;
    private String subCategoryName;
    private String subCategoryDescription;
}
