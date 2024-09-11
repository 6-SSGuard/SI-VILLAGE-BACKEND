package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandsListResponseDto;
import org.example.sivillage.brand.dto.out.GetBrandsResponseDto;
import org.example.sivillage.brand.infrastructure.BrandLikeRepository;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandLikeRepository brandLikeRepository;

    @Override
    public void addBrand(AddBrandRequestDto addBrandRequestDto) {
        validateDuplicatedBrand(addBrandRequestDto);

        Brand brand = addBrandRequestDto.toEntity();
        brandRepository.save(brand);
    }

    @Override
    public GetBrandsListResponseDto getBrands(String memberUuid) {
        List<GetBrandsResponseDto> getBrandsResponseDto = brandRepository.findAllByOrderByEngNameAsc()
                .stream()
                .map(brand -> {
                    // 좋아요 상태를 조회
                    boolean isLiked = brandLikeRepository.findIsLikedByBrandIdAndMemberUuid(brand.getBrandId(), memberUuid)
                            .orElse(false); // 없으면 false 반환
                    return GetBrandsResponseDto.builder()
                            .brandId(brand.getBrandId())
                            .brandEngName(brand.getBrandEngName())
                            .brandKorName(brand.getBrandKorName())
                            .isLiked(isLiked)
                            .build();
                })
                .collect(Collectors.toList());
        return new GetBrandsListResponseDto(getBrandsResponseDto);
    }

    private void validateDuplicatedBrand(AddBrandRequestDto addBrandRequestDto) {
        boolean exist = brandRepository.existsByBrandEngNameOrBrandKorName(addBrandRequestDto.getBrandEngName(), addBrandRequestDto.getBrandKorName());
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
