package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandIdListResponseDto;
import org.example.sivillage.brand.dto.out.GetBrandInfoResponseDto;
import org.example.sivillage.brand.infrastructure.BrandLikeRepository;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandLikeRepository brandLikeRepository;

    /**
     * BrandServiceImpl
     * 1. 브랜드 추가
     * 2. 브랜드 id 목록 조회
     * 3. 브랜드 정보 조회
     */

    /**
     * 브랜드 추가
     * @param addBrandRequestDto
     * return void
     */
    @Override
    public void addBrand(AddBrandRequestDto addBrandRequestDto) {
        validateDuplicatedBrand(addBrandRequestDto);

        Brand brand = addBrandRequestDto.toEntity();
        brandRepository.save(brand);
    }

    private void validateDuplicatedBrand(AddBrandRequestDto addBrandRequestDto) {
        boolean exist = brandRepository.existsByBrandEngNameOrBrandKorName(addBrandRequestDto.getBrandEngName(), addBrandRequestDto.getBrandKorName());
        if (exist) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BRAND_NAME);
        }
    }

    /**
     * 브랜드 id 목록 조회
     * @param memberUuid
     * return GetBrandIdListResponseDto
     */
    @Override
    public List<GetBrandIdListResponseDto> getBrandIdList(String memberUuid) {
//        return brandRepository.findAllByOrderByEngNameAsc()
//                .stream()
//                .map(brand -> {
//                    // 좋아요 상태를 조회
//                    boolean isLiked = brandLikeRepository.findIsLikedByBrandIdAndMemberUuid(brand.getBrandId(), memberUuid)
//                            .orElse(false); // 없으면 false 반환
//                    return GetBrandIdListResponseDto.builder()
//                            .brandId(brand.getBrandId())
//                            .brandEngName(brand.getBrandEngName())
//                            .brandKorName(brand.getBrandKorName())
//                            .isLiked(isLiked)
//                            .build();
//                })
//                .toList();
        return brandRepository.findAllByOrderByEngNameAsc()
                .stream()
                .map(brand -> GetBrandIdListResponseDto.builder()
                        .brandId(brand.getBrandId())
                        .build())
                .toList();
    }

    /**
     * 브랜드 정보 조회(브랜드 영어 명, 한국어 명)
     * @param brandId
     * return GetBrandInfoResponseDto
     */
    @Override
    public GetBrandInfoResponseDto getBrandInfo(Long brandId) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        return GetBrandInfoResponseDto.builder()
                .brandEngName(brand.getBrandEngName())
                .brandKorName(brand.getBrandKorName())
                .build();
    }


}
