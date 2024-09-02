package org.example.sivillage.admin.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.BottomCategory;
import org.example.sivillage.admin.domain.MiddleCategory;
import org.example.sivillage.admin.domain.TopCategory;
import org.example.sivillage.admin.dto.in.AddBottomCategoryRequestDto;
import org.example.sivillage.admin.dto.in.AddMiddleCategoryRequestDto;
import org.example.sivillage.admin.dto.in.AddSubCategoryRequestDto;
import org.example.sivillage.admin.dto.in.TopCategoryRequestDto;
import org.example.sivillage.admin.dto.out.*;
import org.example.sivillage.admin.infrastructure.BottomCategoryRepository;
import org.example.sivillage.admin.infrastructure.MiddleCategoryRepository;
import org.example.sivillage.admin.infrastructure.SubCategoryRepository;
import org.example.sivillage.admin.infrastructure.TopCategoryRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final TopCategoryRepository topCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final BottomCategoryRepository bottomCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ModelMapper mapper;

    public void addTopCategory(TopCategoryRequestDto topCategoryRequestDto) {
        topCategoryRepository.save(topCategoryRequestDto.toEntity());
    }

    public GetTopCategoriesResponseDto getTopCategories() {
        List<TopCategoryDto> topCategories = topCategoryRepository.findAll()
                .stream()
                .map(TopCategoryDto::toDto)
                .collect(Collectors.toList());

        return new GetTopCategoriesResponseDto(topCategories);
    }

    public void addMiddleCategory(AddMiddleCategoryRequestDto request) {
        TopCategory topCategory = topCategoryRepository.findByCategoryCode(request.getTopCategoryCode())
               .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        middleCategoryRepository.save(request.toEntity(topCategory));
    }

    public GetMiddleCategoriesResponseDto getMiddleCategories(String topCategoryCode) {
        TopCategory topCategory = topCategoryRepository.findByCategoryCode(topCategoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        List<MiddleCategoryDto> middleCategories = middleCategoryRepository.findByTopCategory(topCategory)
                .stream()
                .map(MiddleCategoryDto::toDto)
                .collect(Collectors.toList());

        return new GetMiddleCategoriesResponseDto(middleCategories);
    }

    public void addBottomCategory(AddBottomCategoryRequestDto request) {
        MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(request.getMiddleCategoryCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        bottomCategoryRepository.save(request.toEntity(middleCategory));
    }

    public GetBottomCategoriesResponseDto getBottomCategory(String middleCategoryCode) {
        MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(middleCategoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        List<BottomCategoryDto> bottomCategories = bottomCategoryRepository.findByMiddleCategory(middleCategory)
                .stream()
                .map(BottomCategoryDto::toDto)
                .collect(Collectors.toList());

        return new GetBottomCategoriesResponseDto(bottomCategories);
    }

    public void addSubCategory(AddSubCategoryRequestDto request) {
        BottomCategory bottomCategory = bottomCategoryRepository.findByCategoryCode(request.getBottomCategoryCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        subCategoryRepository.save(request.toEntity(bottomCategory));
    }

    public GetSubCategoriesResponseDto getSubCategories(String bottomCategoryCode) {
        BottomCategory bottomCategory = bottomCategoryRepository.findByCategoryCode(bottomCategoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));


        List<SubCategoryDto> subCategories = subCategoryRepository.findByBottomCategory(bottomCategory)
                .stream()
                .map(SubCategoryDto::toDto)
                .collect(Collectors.toList());

        return new GetSubCategoriesResponseDto(subCategories);
    }
}
