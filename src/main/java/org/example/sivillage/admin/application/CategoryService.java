package org.example.sivillage.admin.application;

import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;
import org.example.sivillage.admin.dto.out.GetSubCategoriesResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    void addCategory(AddCategoryRequestDto addCategoryRequestDto);
    List<GetSubCategoriesResponseDto> getSubCategories(String parentCategoryCode);
    void addCategoryFromFile(MultipartFile file);
}