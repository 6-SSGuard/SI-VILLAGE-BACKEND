package org.example.sivillage.admin.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.BottomCategory;
import org.example.sivillage.admin.domain.MiddleCategory;
import org.example.sivillage.admin.domain.TopCategory;
import org.example.sivillage.admin.dto.in.AddBottomCategoryRequestDto;
import org.example.sivillage.admin.dto.in.AddMiddleCategoryRequestDto;
import org.example.sivillage.admin.dto.in.TopCategoryRequestDto;
import org.example.sivillage.admin.dto.out.BottomCategoryResponseDto;
import org.example.sivillage.admin.dto.out.MiddleCategoryResponseDto;
import org.example.sivillage.admin.dto.out.TopCategoryResponseDto;
import org.example.sivillage.admin.infrastructure.BottomCategoryRepository;
import org.example.sivillage.admin.infrastructure.MiddleCategoryRepository;
import org.example.sivillage.admin.infrastructure.TopCategoryRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final TopCategoryRepository topCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final BottomCategoryRepository bottomCategoryRepository;
    private final ModelMapper mapper;

    public void addTopCategory(TopCategoryRequestDto topCategoryRequestDto) {
        topCategoryRepository.save(topCategoryRequestDto.toEntity());
    }

    public TopCategoryResponseDto getTopCategory(String topCategoryCode) {
        TopCategory topCategory = topCategoryRepository.findByCategoryCode(topCategoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        return TopCategoryResponseDto.toDto(topCategory);
    }

    public void addMiddleCategory(AddMiddleCategoryRequestDto request) {
        TopCategory topCategory = topCategoryRepository.findByCategoryCode(request.getTopCategoryCode())
               .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        middleCategoryRepository.save(request.toEntity(topCategory));
    }

    public MiddleCategoryResponseDto getMiddleCategory(String middleCategoryCode) {
        MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(middleCategoryCode)
               .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        return MiddleCategoryResponseDto.toDto(middleCategory);
    }

    public void addBottomCategory(AddBottomCategoryRequestDto request) {
        MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(request.getMiddleCategoryCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        bottomCategoryRepository.save(request.toEntity(middleCategory));
    }

    public BottomCategoryResponseDto getBottomCategory(String bottomCategoryCode) {
        BottomCategory bottomCategory = bottomCategoryRepository.findByCategoryCode(bottomCategoryCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        return BottomCategoryResponseDto.toDto(bottomCategory);
    }
}
