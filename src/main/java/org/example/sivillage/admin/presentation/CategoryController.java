package org.example.sivillage.admin.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.CategoryService;
import org.example.sivillage.admin.dto.in.AddBottomCategoryRequestDto;
import org.example.sivillage.admin.dto.in.AddMiddleCategoryRequestDto;
import org.example.sivillage.admin.dto.in.TopCategoryRequestDto;
import org.example.sivillage.admin.dto.out.BottomCategoryResponseDto;
import org.example.sivillage.admin.dto.out.MiddleCategoryResponseDto;
import org.example.sivillage.admin.dto.out.TopCategoryResponseDto;
import org.example.sivillage.admin.vo.*;
import org.example.sivillage.global.common.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Operation(summary = "대 카테고리 생성")
    @PostMapping("/top-category")
    public BaseResponse<Void> addTopCategory(
            @RequestBody TopCategoryRequestVo topCategoryRequestVo) {

        TopCategoryRequestDto topCategoryRequestDto = TopCategoryRequestDto.builder()
                .topCategoryName(topCategoryRequestVo.getTopCategoryName())
                .topCategoryDescription(topCategoryRequestVo.getTopCategoryDescription())
                .build();

        categoryService.addTopCategory(topCategoryRequestDto);

        return new BaseResponse<>();
    }

    @Operation(summary = "대 카테고리 열람")
    @GetMapping("/top-category/{topCategoryCode}")
    public BaseResponse<TopCategoryResponseVo> getTopCategory(
            @PathVariable String topCategoryCode) {

        TopCategoryResponseDto responseDto = categoryService.getTopCategory(topCategoryCode);
        TopCategoryResponseVo response = mapper.map(responseDto, TopCategoryResponseVo.class);

        return new BaseResponse<>(response);
    }

    @Operation(summary = "중 카테고리 생성")
    @PostMapping("/middle-category")
    public BaseResponse<Void> addMiddleCategory(
            @RequestBody AddMiddleCategoryRequestVo addMiddleCategoryRequestVo) {
        AddMiddleCategoryRequestDto request = mapper.map(addMiddleCategoryRequestVo, AddMiddleCategoryRequestDto.class);
        categoryService.addMiddleCategory(request);

        return new BaseResponse<>();
    }

    @Operation(summary = "중 카테고리 조회")
    @GetMapping("/middle-category/{middleCategoryCode}")
    public BaseResponse<MiddleCategoryResponseVo> getMiddleCategory(
            @PathVariable String middleCategoryCode) {

        MiddleCategoryResponseDto responseDto = categoryService.getMiddleCategory(middleCategoryCode);
        MiddleCategoryResponseVo response = mapper.map(responseDto, MiddleCategoryResponseVo.class);

        return new BaseResponse<>(response);
    }

    @Operation(summary = "소 카테고리 생성")
    @PostMapping("/bottom-category")
    public BaseResponse<Void> addBottomCategory(
            @RequestBody AddBottomCategoryRequestVo addBottomCategoryRequestVo) {
        AddBottomCategoryRequestDto request = mapper.map(addBottomCategoryRequestVo, AddBottomCategoryRequestDto.class);
        categoryService.addBottomCategory(request);

        return new BaseResponse<>();
    }

    @Operation(summary = "소 카테고리 조회")
    @GetMapping("/bottom-category/{bottomCategoryCode}")
    public BaseResponse<BottomCategoryResponseVo> getBottomCategory(
            @PathVariable String bottomCategoryCode) {

        BottomCategoryResponseDto responseDto = categoryService.getBottomCategory(bottomCategoryCode);
        BottomCategoryResponseVo response = mapper.map(responseDto, BottomCategoryResponseVo.class);

        return new BaseResponse<>(response);
    }
}