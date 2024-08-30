package org.example.sivillage.admin.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.MiddleCategory;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiddleCategoryResponseDto {
    private String topCategoryCode;
    private String middleCategoryName;
    private String middleCategoryDescription;
    private String middleCategoryCode;

    public static MiddleCategoryResponseDto toDto(MiddleCategory middleCategory) {
        return MiddleCategoryResponseDto.builder()
                .topCategoryCode(middleCategory.getTopCategory().getCategoryCode())
                .middleCategoryName(middleCategory.getCategoryName())
                .middleCategoryDescription(middleCategory.getCategoryDescription())
                .middleCategoryCode(middleCategory.getCategoryCode())
                .build();
    }
}