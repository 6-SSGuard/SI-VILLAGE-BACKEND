package org.example.sivillage.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MiddleCategoryResponseVo {
    private String topCategoryCode;
    private String middleCategoryCode;
    private String middleCategoryName;
    private String middleCategoryDescription;
}
