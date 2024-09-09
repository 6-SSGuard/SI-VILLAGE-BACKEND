package org.example.sivillage.admin.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.CategoryService;
import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;
import org.example.sivillage.admin.dto.out.GetSubCategoriesResponseDto;
import org.example.sivillage.admin.vo.AddCategoryRequestVo;
import org.example.sivillage.admin.vo.GetSubCategoriesResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@Tag(name = "카테고리 관리 API", description = "카테고리 관련 API endpoints")
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Operation(summary = "카테고리 생성", description = "parentCategoryCode =\"\"입력시 최상위 카테고리로 생성")
    @PostMapping("/")
    public BaseResponse<Void> addCategory(
            @RequestBody AddCategoryRequestVo addCategoryRequestVo) {
        AddCategoryRequestDto request = mapper.map(addCategoryRequestVo, AddCategoryRequestDto.class);
        categoryService.addCategory(request);
        return new BaseResponse<>();
    }

    @Operation(summary = "JSON 파일 기반으로 카테고리 생성")
    @PostMapping(value = "/json", consumes = "multipart/form-data")
    public BaseResponse<Void> addCategoryFromFile(@RequestPart("file") MultipartFile file) {
        categoryService.addCategoryFromFile(file);
        return new BaseResponse<>();
    }

    @Operation(summary = "하위 카테고리 리스트 조회", description = "parentCategoryName =\"\"입력시 최상위 카테고리 리스트 조회")
    @GetMapping("/sub-categories")
    public BaseResponse<GetSubCategoriesResponseVo> getSubCategories(
            @RequestParam(value = "parentCategoryName", required = false) String parentCategoryName) {
        GetSubCategoriesResponseDto responseDto = categoryService.getSubCategories(parentCategoryName);
        GetSubCategoriesResponseVo response = mapper.map(responseDto, GetSubCategoriesResponseVo.class);
        return new BaseResponse<>(response);
    }
}