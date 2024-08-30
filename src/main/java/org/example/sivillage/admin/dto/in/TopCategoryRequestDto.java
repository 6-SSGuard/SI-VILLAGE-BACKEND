package org.example.sivillage.admin.dto.in;

import lombok.*;
import org.example.sivillage.admin.domain.TopCategory;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopCategoryRequestDto {
    private String topCategoryName;
    private String topCategoryDescription;
    private String topCategoryCode;

    //toEntity
    public TopCategory toEntity() {
        return TopCategory.builder()
                .categoryName(topCategoryName)
                .categoryDescription(topCategoryDescription)
                .categoryCode(generateTopCategoryCode())
                .build();
    }

    public String generateTopCategoryCode() {
        return "TC" + UUID.randomUUID().toString().substring(0, 8);
    }
}
