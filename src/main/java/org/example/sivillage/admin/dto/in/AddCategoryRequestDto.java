package org.example.sivillage.admin.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCategoryRequestDto {
    private String categoryName;
    private String categoryDescription;
    private String parentCategoryCode;
}
