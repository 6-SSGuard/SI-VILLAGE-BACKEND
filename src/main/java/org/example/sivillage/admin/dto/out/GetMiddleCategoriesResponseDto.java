package org.example.sivillage.admin.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMiddleCategoriesResponseDto {
    private List<MiddleCategoryDto> middleCategories;
}