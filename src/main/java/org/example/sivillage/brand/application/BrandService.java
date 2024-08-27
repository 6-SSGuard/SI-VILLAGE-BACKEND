package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandsResponseDto;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.error.CustomException;
import org.example.sivillage.global.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public void addBrand(AddBrandRequestDto request) {
        validateDuplicatedBrand(request);

        Brand brand = Brand.toEntity(request);
        brandRepository.save(brand);
    }

    public List<GetBrandsResponseDto> getBrands(String initial) {

        if (initial == null) {
            return getAllBrands();
        }

        char firstChar = initial.charAt(0);
        return isEnglishCharacter(firstChar)
                ? getBrandsByEngInitial(firstChar)
                : getBrandsByKorInitial(firstChar);
    }

    private void validateDuplicatedBrand(AddBrandRequestDto request) {
        boolean exist = brandRepository.existsByBrandEngNameOrBrandKorName(request.getBrandEngName(), request.getBrandKorName());
        if (exist) {
            throw new CustomException(ErrorCode.DUPLICATE_BRAND_NAME);
        }
    }

    private boolean isEnglishCharacter(char c) {
        return Character.isAlphabetic(c) && c <= 'z'; // a~z 범위 체크
    }

    private List<GetBrandsResponseDto> getBrandsByEngInitial(char initial) {
        return brandRepository.findByEngNameStartingWith(String.valueOf(initial))
                .stream()
                .map(GetBrandsResponseDto::toDto)
                .collect(Collectors.toList());
    }

    private List<GetBrandsResponseDto> getBrandsByKorInitial(char initial) {
        return brandRepository.findByKorNameStartingWith(String.valueOf(initial))
                .stream()
                .map(GetBrandsResponseDto::toDto)
                .collect(Collectors.toList());
    }

    private List<GetBrandsResponseDto> getAllBrands() {
        return brandRepository.findAllByOrderByEngName()
                .stream()
                .map(GetBrandsResponseDto::toDto)
                .collect(Collectors.toList());
    }
}
