package org.example.sivillage.admin.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;
import org.example.sivillage.admin.dto.out.CategoryDto;
import org.example.sivillage.admin.dto.out.GetSubCategoriesResponseDto;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public void addCategory(AddCategoryRequestDto request) {
        Category category;

        if (request.getParentCategoryCode().equals("top")) {
            category = Category.createRootCategory(request);
        }
        else {
            Category parentCategory = findCategoryByCategoryCode(request.getParentCategoryCode());
            category = Category.createChildCategory(request, parentCategory);
        }

        categoryRepository.save(category);
    }

    public GetSubCategoriesResponseDto getSubCategories(String parentCategoryCode) {
        List<CategoryDto> categories;

        if (parentCategoryCode.equals("top")) {
            // 최상위 카테고리를 가져옴
            categories = categoryRepository.findByParentIsNull()
                    .stream()
                    .map(CategoryDto::toDto)
                    .collect(Collectors.toList());
        } else {
            // 특정 부모 카테고리의 하위 카테고리를 가져옴
            Category parentCategory = findCategoryByCategoryCode(parentCategoryCode);
            categories = categoryRepository.findByParent(parentCategory)
                    .stream()
                    .map(CategoryDto::toDto)
                    .collect(Collectors.toList());
        }

        return new GetSubCategoriesResponseDto(categories);
    }

    private Category findCategoryByCategoryCode(String categoryCode) {
        return categoryRepository.findByCategoryCode(categoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));
    }
}
