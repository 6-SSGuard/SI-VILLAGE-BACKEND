package org.example.sivillage.admin.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.CategoryService;
import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;
import org.example.sivillage.admin.dto.out.GetSubCategoriesResponseDto;
import org.example.sivillage.admin.vo.AddCategoryRequestVo;
import org.example.sivillage.admin.vo.GetSubCategoriesResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Operation(summary = "카테고리 생성", description = "parentCategoryCode =\"top\"입력시 최상위 카테고리 생성")
    @PostMapping("/")
    public BaseResponse<Void> addCategory(
            @RequestBody AddCategoryRequestVo addCategoryRequestVo) {
        AddCategoryRequestDto request = mapper.map(addCategoryRequestVo, AddCategoryRequestDto.class);
        categoryService.addCategory(request);
        return new BaseResponse<>();
    }

    @Operation(summary = "하위 카테고리 리스트 조회", description = "parentCategoryCode =\"top\"입력시 최상위 카테고리 리스트 조회")
    @GetMapping("/{parentCategoryCode}")
    public BaseResponse<GetSubCategoriesResponseVo> getSubCategories(
            @PathVariable String parentCategoryCode) {
        GetSubCategoriesResponseDto responseDto = categoryService.getSubCategories(parentCategoryCode);
        GetSubCategoriesResponseVo response = mapper.map(responseDto, GetSubCategoriesResponseVo.class);
        return new BaseResponse<>(response);
    }
}