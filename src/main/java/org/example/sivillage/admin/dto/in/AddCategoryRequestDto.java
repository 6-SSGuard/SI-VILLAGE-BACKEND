package org.example.sivillage.admin.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.global.common.UuidGenerator;

@Getter
@NoArgsConstructor
public class AddCategoryRequestDto {
    private String categoryName;
    private String parentCategoryCode;

    @Builder
    public AddCategoryRequestDto(String categoryName, String parentCategoryCode) {
        this.categoryName = categoryName;
        this.parentCategoryCode = parentCategoryCode;
    }

    public Category createRootCategory() {
        return Category.builder()
                .categoryName(categoryName)
                .categoryCode(UuidGenerator.generateCategoryCode())
                .depth(0)
                .parent(null)
                .build();
    }

    public Category createChildCategory(Category parentCategory) {
        return Category.builder()
                .categoryName(categoryName)
                .categoryCode(UuidGenerator.generateCategoryCode())
                .parent(parentCategory)
                .depth(parentCategory.getDepth() + 1)
                .build();
    }
}
