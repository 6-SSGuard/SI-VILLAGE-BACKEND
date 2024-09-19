package org.example.sivillage.admin.application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;
import org.example.sivillage.admin.dto.out.GetSubCategoriesResponseDto;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(AddCategoryRequestDto addCategoryRequestDto) {

        if (Boolean.TRUE.equals(categoryRepository.existsByCategoryName(addCategoryRequestDto.getCategoryName()))) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_CATEGORY_NAME);
        }

        if (addCategoryRequestDto.getParentCategoryCode().isEmpty()) {
            categoryRepository.save(addCategoryRequestDto.createRootCategory());
            return;
        }

        Category parentCategory = findCategoryByCategoryCode(addCategoryRequestDto.getParentCategoryCode());
        categoryRepository.save(addCategoryRequestDto.createChildCategory(parentCategory));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetSubCategoriesResponseDto> getSubCategories(String parentCategoryCode) {

        if (parentCategoryCode.equals("top")) {
            // 최상위 카테고리를 가져옴
            return categoryRepository.findByParentIsNull()
                    .stream()
                    .map(GetSubCategoriesResponseDto::from)
                    .toList();
        }

        // 특정 부모 카테고리의 하위 카테고리를 가져옴
        Category parentCategory = findCategoryByCategoryCode(parentCategoryCode);
        return categoryRepository.findByParent(parentCategory)
                .stream()
                .map(GetSubCategoriesResponseDto::from)
                .toList();
    }

    @Override
    public void addCategoryFromFile(MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".json")) {
            throw new BaseException(BaseResponseStatus.INVALID_FILE_FORMAT);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String jsonData = reader.lines().collect(Collectors.joining());
            parseAndSaveCategory(jsonData);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FILE_PARSE_FAILED);
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
            throw new BaseException(BaseResponseStatus.FILE_PARSE_FAILED);
        }
    }

    private void saveCategoryTree(JsonNode node, Category parentCategory) {
        if (node.has("categoryName")) {
            String categoryName = node.get("categoryName").asText();
            AddCategoryRequestDto requestDto = createRequestDto(categoryName, parentCategory);

            Category category = addCategoryWithParentCategory(requestDto, parentCategory);

            if (node.has("subCategories")) {
                for (JsonNode childNode : node.get("subCategories")) {
                    saveCategoryTree(childNode, category);
                }
            }
        }
    }

    private Category addCategoryWithParentCategory(AddCategoryRequestDto addCategoryRequestDto, Category parentCategory) {

        if (parentCategory == null || addCategoryRequestDto.getParentCategoryCode() == null) {
            return categoryRepository.save(addCategoryRequestDto.createRootCategory());
        }

        return categoryRepository.save(addCategoryRequestDto.createChildCategory(parentCategory));
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
