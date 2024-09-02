package org.example.sivillage.admin.vo;

import lombok.*;
import org.example.sivillage.admin.dto.out.CategoryDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSubCategoriesResponseVo {
    private List<CategoryDto> categories;
}

