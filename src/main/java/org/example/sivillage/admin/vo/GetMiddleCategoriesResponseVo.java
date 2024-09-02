package org.example.sivillage.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sivillage.admin.dto.out.MiddleCategoryDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetMiddleCategoriesResponseVo {
    private List<MiddleCategoryDto> middleCategories;
}
