package org.example.sivillage.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BottomCategoryResponseVo {
    private String middleCategoryCode;
    private String bottomCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;
}
