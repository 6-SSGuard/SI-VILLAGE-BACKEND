package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.SubCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryResponseDto {
    private String bottomCategoryCode;
    private String subCategoryName;
    private String subCategoryDescription;
    private String subCategoryCode;

    public static SubCategoryResponseDto toDto(SubCategory subCategory) {
        return SubCategoryResponseDto.builder()
                .bottomCategoryCode(subCategory.getBottomCategory().getCategoryCode())
                .subCategoryName(subCategory.getCategoryName())
                .subCategoryDescription(subCategory.getCategoryDescription())
                .subCategoryCode(subCategory.getCategoryCode())
                .build();
    }
}
