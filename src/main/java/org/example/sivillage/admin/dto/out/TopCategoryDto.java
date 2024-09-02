package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.TopCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TopCategoryDto {
    private String topCategoryCode;
    private String topCategoryName;
    private String topCategoryDescription;

    public static TopCategoryDto toDto(TopCategory topCategory) {
        return TopCategoryDto.builder()
                .topCategoryCode(topCategory.getCategoryCode())
                .topCategoryName(topCategory.getCategoryName())
                .topCategoryDescription(topCategory.getCategoryDescription())
                .build();
    }
}
