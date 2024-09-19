package org.example.sivillage.admin.vo.in;

import lombok.*;
import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;

@Getter
@NoArgsConstructor
public class AddCategoryRequestVo {
    private String categoryName;
    private String parentCategoryCode;

    @Builder
    public AddCategoryRequestDto toDto() {
        return AddCategoryRequestDto.builder()
                .categoryName(categoryName)
                .parentCategoryCode(parentCategoryCode)
                .build();
    }
}
