package org.example.sivillage.admin.application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;
import org.example.sivillage.admin.dto.out.CategoryDto;
import org.example.sivillage.admin.dto.out.GetSubCategoriesResponseDto;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


//    public void addCategory(AddCategoryRequestDto request) {
//        Category category;
//
//        if (request.getParentCategoryCode().equals("top")) {
//            category = Category.createRootCategory(request);
//        }
//        else {
//            Category parentCategory = findCategoryByCategoryCode(request.getParentCategoryCode());
//            category = Category.createChildCategory(request, parentCategory);
//        }
//
//        categoryRepository.save(category);
//    }

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

    public void addCategoryFromFile(MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".json")) {
            throw new BaseException(BaseResponseStatus.INVALID_FILE_FORMAT);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String jsonData = reader.lines().collect(Collectors.joining());
            parseAndSaveCategory(jsonData);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.JSON_PARSE_FAILED);
        }
    }

    private void parseAndSaveCategory(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonData);
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    saveCategoryTree(node, null);
                }
            } else {
                saveCategoryTree(rootNode, null);
            }
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.JSON_PARSE_FAILED);
        }
    }

    private void saveCategoryTree(JsonNode node, Category parentCategory) {
        if (node.has("categoryName")) {
            String categoryName = node.get("categoryName").asText();
            AddCategoryRequestDto requestDto = createRequestDto(categoryName, parentCategory);

            Category category = addCategory(requestDto, parentCategory);

            if (node.has("subCategories")) {
                for (JsonNode childNode : node.get("subCategories")) {
                    saveCategoryTree(childNode, category);
                }
            }
        }
    }

    public Category addCategory(AddCategoryRequestDto request, Category parentCategory) {
        Category category;

        if (parentCategory == null || request.getParentCategoryCode() == null) {
            category = Category.createRootCategory(request);
        } else {
            category = Category.createChildCategory(request, parentCategory);
        }

        return categoryRepository.save(category);
    }

    private AddCategoryRequestDto createRequestDto(String categoryName, Category parentCategory) {
        String parentCategoryCode = parentCategory != null ? parentCategory.getCategoryCode() : null;
        return new AddCategoryRequestDto(categoryName, parentCategoryCode);
    }


    private Category findCategoryByCategoryCode(String categoryCode) {
        return categoryRepository.findByCategoryCode(categoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));
    }

}
