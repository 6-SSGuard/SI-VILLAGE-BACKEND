package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.BottomCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BottomCategoryResponseDto {
    private String middleCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;
    private String bottomCategoryCode;

    public static BottomCategoryResponseDto toDto(BottomCategory bottomCategory) {
        return BottomCategoryResponseDto.builder()
                .middleCategoryCode(bottomCategory.getMiddleCategory().getCategoryCode())
                .bottomCategoryName(bottomCategory.getCategoryName())
                .bottomCategoryDescription(bottomCategory.getCategoryDescription())
                .bottomCategoryCode(bottomCategory.getCategoryCode())
                .build();
    }
}
