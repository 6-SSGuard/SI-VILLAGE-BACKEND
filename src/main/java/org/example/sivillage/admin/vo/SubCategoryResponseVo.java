package org.example.sivillage.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubCategoryResponseVo {
    private String bottomCategoryCode;
    private String subCategoryCode;
    private String subCategoryName;
    private String subCategoryDescription;
}
