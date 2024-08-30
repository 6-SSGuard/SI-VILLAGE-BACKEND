package org.example.sivillage.admin.dto.in;

import lombok.*;
import org.example.sivillage.admin.domain.MiddleCategory;
import org.example.sivillage.admin.domain.TopCategory;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddMiddleCategoryRequestDto {
    private String topCategoryCode;
    private String middleCategoryCode;
    private String middleCategoryName;
    private String middleCategoryDescription;

    public MiddleCategory toEntity(TopCategory topCategory) {
        return MiddleCategory.builder()
                .topCategory(topCategory)
                .categoryName(middleCategoryName)
                .categoryCode(generateMiddleCategoryCode())
                .categoryDescription(middleCategoryDescription)
                .build();
    }

    private String generateMiddleCategoryCode() {
        return "MC" + UUID.randomUUID().toString().substring(0, 8);
    }
}
