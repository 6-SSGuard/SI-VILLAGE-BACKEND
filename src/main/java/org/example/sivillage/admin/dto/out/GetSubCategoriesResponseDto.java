package org.example.sivillage.admin.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSubCategoriesResponseDto {
    private List<SubCategoryDto> subCategories;
}
