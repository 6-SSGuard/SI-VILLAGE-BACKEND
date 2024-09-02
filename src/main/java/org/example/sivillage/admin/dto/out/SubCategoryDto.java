package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.SubCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubCategoryDto {
    private String subCategoryCode;
    private String subCategoryName;
    private String subCategoryDescription;

    public static SubCategoryDto toDto(SubCategory subCategory) {
        return SubCategoryDto.builder()
                .subCategoryCode(subCategory.getCategoryCode())
                .subCategoryName(subCategory.getCategoryName())
                .subCategoryDescription(subCategory.getCategoryDescription())
                .build();
    }
}
