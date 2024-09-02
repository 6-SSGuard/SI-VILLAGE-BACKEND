package org.example.sivillage.admin.vo;

import lombok.*;
import org.example.sivillage.admin.dto.out.TopCategoryDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetTopCategoriesResponseVo {
    private List<TopCategoryDto> topCategories;
}