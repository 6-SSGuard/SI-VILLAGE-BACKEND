package org.example.sivillage.admin.dto.in;

import lombok.*;
import org.example.sivillage.admin.domain.BottomCategory;
import org.example.sivillage.admin.domain.SubCategory;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddSubCategoryRequestDto {
    private String bottomCategoryCode;
    private String subCategoryCode;
    private String subCategoryName;
    private String subCategoryDescription;

    public SubCategory toEntity(BottomCategory bottomCategory) {
        return SubCategory.builder()
                .bottomCategory(bottomCategory)
                .categoryName(subCategoryName)
                .categoryCode(generateBottomCategoryCode())
                .categoryDescription(subCategoryDescription)
                .build();
    }

    private String generateBottomCategoryCode() {
        return "SC" + UUID.randomUUID().toString().substring(0, 8);
    }
}
