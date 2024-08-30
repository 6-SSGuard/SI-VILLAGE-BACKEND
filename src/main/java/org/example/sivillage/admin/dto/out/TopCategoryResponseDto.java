package org.example.sivillage.admin.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.TopCategory;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopCategoryResponseDto {
    private String topCategoryName;
    private String topCategoryDescription;
    private String topCategoryCode;

    public static TopCategoryResponseDto toDto(TopCategory topCategory) {
        return TopCategoryResponseDto.builder()
                .topCategoryName(topCategory.getCategoryName())
                .topCategoryDescription(topCategory.getCategoryDescription())
                .topCategoryCode(topCategory.getCategoryCode())
                .build();
    }
}
