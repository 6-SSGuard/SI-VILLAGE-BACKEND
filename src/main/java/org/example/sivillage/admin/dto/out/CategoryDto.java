package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String categoryCode;
    private String categoryName;
    private String categoryDescription;
    private int depth;


    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .categoryCode(category.getCategoryCode())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .depth(category.getDepth())
                .build();
    }
}
