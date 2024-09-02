package org.example.sivillage.admin.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetTopCategoriesResponseDto {
    private List<TopCategoryDto> topCategories;
}

