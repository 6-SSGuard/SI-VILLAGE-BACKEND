package org.example.sivillage.admin.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequestDto {
    private String categoryName;
    private String parentCategoryName;
}
