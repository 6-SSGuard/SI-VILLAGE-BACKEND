package org.example.sivillage.admin.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.CategoryServiceImpl;
import org.example.sivillage.admin.dto.out.GetSubCategoriesResponseDto;
import org.example.sivillage.admin.vo.in.AddCategoryRequestVo;
import org.example.sivillage.admin.vo.out.GetSubCategoriesResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "카테고리 관리 API", description = "카테고리 관련 API endpoints")
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    @Operation(summary = "카테고리 생성", description = "parentCategoryCode =\"\"입력시 최상위 카테고리 생성")
    @PostMapping("/")
    public BaseResponse<Void> addCategory(
            @RequestBody AddCategoryRequestVo addCategoryRequestVo) {

        categoryServiceImpl.addCategory(addCategoryRequestVo.toDto());
        return new BaseResponse<>();
    }

    @Operation(summary = "JSON 파일 기반으로 카테고리 생성")
    @PostMapping(value = "/json", consumes = "multipart/form-data")
    public BaseResponse<Void> addCategoryFromFile(@RequestPart("file") MultipartFile file) {
        categoryServiceImpl.addCategoryFromFile(file);
        return new BaseResponse<>();
    }

    @Operation(summary = "하위 카테고리 리스트 조회", description = "parentCategoryCode =\"\"입력시 최상위 카테고리 리스트 조회")
    @GetMapping("/{parentCategoryCode}")
    public BaseResponse<List<GetSubCategoriesResponseVo>> getSubCategories(
            @PathVariable String parentCategoryCode) {
        return new BaseResponse<>(
                categoryServiceImpl.getSubCategories(parentCategoryCode)
                .stream()
                .map(GetSubCategoriesResponseDto::toVo)
                .toList()
        );
    }
}