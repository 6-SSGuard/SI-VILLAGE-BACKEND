package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.MiddleCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MiddleCategoryDto {
    private String middleCategoryCode;
    private String middleCategoryName;
    private String middleCategoryDescription;

    public static MiddleCategoryDto toDto(MiddleCategory middleCategory) {
        return MiddleCategoryDto.builder()
                .middleCategoryCode(middleCategory.getCategoryCode())
                .middleCategoryName(middleCategory.getCategoryName())
                .middleCategoryDescription(middleCategory.getCategoryDescription())
                .build();
    }
}
