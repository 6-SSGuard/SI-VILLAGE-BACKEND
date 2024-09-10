package org.example.sivillage.admin.dto.out;

import lombok.*;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.admin.vo.out.GetSubCategoriesResponseVo;

@Getter
@NoArgsConstructor
public class GetSubCategoriesResponseDto {
    private String categoryName;
    private String categoryCode;

    @Builder
    public GetSubCategoriesResponseDto(String categoryName, String categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public static GetSubCategoriesResponseDto from(Category category) {
        return GetSubCategoriesResponseDto.builder()
                .categoryCode(category.getCategoryCode())
                .categoryName(category.getCategoryName())
                .build();
    }

    public static GetSubCategoriesResponseVo toVo(GetSubCategoriesResponseDto getSubCategoriesResponseDto) {
        return GetSubCategoriesResponseVo.builder()
                .categoryCode(getSubCategoriesResponseDto.getCategoryCode())
                .categoryName(getSubCategoriesResponseDto.getCategoryName())
                .build();
    }
}
