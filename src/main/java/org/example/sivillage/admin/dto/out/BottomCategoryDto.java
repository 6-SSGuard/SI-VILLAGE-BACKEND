package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.BottomCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BottomCategoryDto {
    private String bottomCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;

    public static BottomCategoryDto toDto(BottomCategory bottomCategory) {
        return BottomCategoryDto.builder()
                .bottomCategoryCode(bottomCategory.getCategoryCode())
                .bottomCategoryName(bottomCategory.getCategoryName())
                .bottomCategoryDescription(bottomCategory.getCategoryDescription())
                .build();
    }
}
