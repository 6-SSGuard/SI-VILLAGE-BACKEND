package org.example.sivillage.admin.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBottomCategoriesResponseDto {
    private List<BottomCategoryDto> bottomCategories;
}
