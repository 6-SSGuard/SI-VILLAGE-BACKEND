package org.example.sivillage.admin.dto.in;

import lombok.*;
import org.example.sivillage.admin.domain.BottomCategory;
import org.example.sivillage.admin.domain.MiddleCategory;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBottomCategoryRequestDto {
    private String middleCategoryCode;
    private String bottomCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;

    public BottomCategory toEntity(MiddleCategory middleCategory) {
        return BottomCategory.builder()
                .middleCategory(middleCategory)
                .categoryName(bottomCategoryName)
                .categoryCode(generateBottomCategoryCode())
                .categoryDescription(bottomCategoryDescription)
                .build();
    }

    private String generateBottomCategoryCode() {
        return "BC" + UUID.randomUUID().toString().substring(0, 8);
    }
}
