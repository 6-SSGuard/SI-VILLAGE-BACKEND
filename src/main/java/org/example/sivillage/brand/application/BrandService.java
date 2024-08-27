package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandsResponseDto;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
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

    public List<GetBrandsResponseDto> getBrands() {

        return brandRepository.findAllByOrderByEngNameAsc()
                .stream()
                .map(GetBrandsResponseDto::toDto)
                .collect(Collectors.toList());
    }

    private void validateDuplicatedBrand(AddBrandRequestDto request) {
        boolean exist = brandRepository.existsByBrandEngNameOrBrandKorName(request.getBrandEngName(), request.getBrandKorName());
        if (exist) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BRAND_NAME);
        }
    }

//    private boolean isEnglishCharacter(char c) {
//        return Character.isAlphabetic(c) && c <= 'z'; // a~z 범위 체크
//    }
//
//    private List<GetBrandsResponseDto> getBrandsByEngInitial(char initial) {
//        return brandRepository.findByEngNameStartingWith(String.valueOf(initial))
//                .stream()
//                .map(GetBrandsResponseDto::toDto)
//                .collect(Collectors.toList());
//    }
//
//    private List<GetBrandsResponseDto> getBrandsByKorInitial(char initial) {
//        return brandRepository.findByKorNameStartingWith(String.valueOf(initial))
//                .stream()
//                .map(GetBrandsResponseDto::toDto)
//                .collect(Collectors.toList());
//    }
}
