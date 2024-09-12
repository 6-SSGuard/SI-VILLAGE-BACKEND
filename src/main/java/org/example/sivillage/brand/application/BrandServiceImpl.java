package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.domain.BrandLike;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandLikeResponseDto;
import org.example.sivillage.brand.dto.out.GetBrandNameResponseDto;
import org.example.sivillage.brand.infrastructure.BrandLikeRepository;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
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
     * 3. 브랜드 이름 조회 (브랜드 영어 명, 한국어 명)
     * 4. 브랜드 좋아요 여부 조회
     * 5. 브랜드 좋아요 토글
     */

    /**
     * 1. 브랜드 추가
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
     * 2. 브랜드 id 목록 조회
     * @param memberUuid 회원 UUID
     * return GetBrandIdListResponseDto
     */
    @Override
    public List<IdListResponseDto<Long>> getBrandIdList(String memberUuid) {

        return brandRepository.findAllBrandIdsByOrderByEngNameAsc()
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    /**
     * 3. 브랜드 이름 조회(브랜드 영어 명, 한국어 명)
     * @param brandId 브랜드 ID
     * return GetBrandNameResponseDto
     */
    @Override
    public GetBrandNameResponseDto getBrandName(Long brandId) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        return GetBrandNameResponseDto.builder()
                .brandEngName(brand.getBrandEngName())
                .brandKorName(brand.getBrandKorName())
                .build();
    }

    /**
     * 4. 브랜드 좋아요 여부 조회
     * @param brandId 브랜드 ID
     * @param memberUuid 회원 UUID
     * return GetBrandLikeResponseDto
     */
    @Override
    public GetBrandLikeResponseDto getBrandLike(Long brandId, String memberUuid) {
        BrandLike brandLike = brandLikeRepository.findByBrandIdAndMemberUuid(brandId, memberUuid)
                .orElse(BrandLike.toEntity(brandId, memberUuid));

        return GetBrandLikeResponseDto.builder()
                .like(brandLike.isLiked())
                .build();
    }

    /**
     * 5. 브랜드 좋아요 토글
     * @param brandId 브랜드 ID
     * @param memberUuid 회원 UUID
     * return void
     */
    @Override
    public void toggleBrandLike(Long brandId, String memberUuid) {
        BrandLike brandLike = brandLikeRepository.findByBrandIdAndMemberUuid(brandId, memberUuid)
                .orElse(BrandLike.toEntity(brandId, memberUuid));

        // 현재 상태를 토글 (좋아요 -> 싫어요, 싫어요 -> 좋아요)
        brandLike.toggleLike();

        brandLikeRepository.save(brandLike);
    }
}
