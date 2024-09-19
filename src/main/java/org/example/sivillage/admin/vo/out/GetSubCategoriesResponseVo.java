package org.example.sivillage.admin.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetSubCategoriesResponseVo {
    private String categoryName;
    private String categoryCode;

    @Builder
    public GetSubCategoriesResponseVo(String categoryName, String categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }
}

