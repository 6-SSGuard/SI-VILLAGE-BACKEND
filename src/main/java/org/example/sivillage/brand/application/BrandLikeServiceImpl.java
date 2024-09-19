package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.brand.domain.BrandLike;
import org.example.sivillage.brand.dto.out.GetBrandLikeResponseDto;
import org.example.sivillage.brand.infrastructure.BrandLikeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandLikeServiceImpl implements BrandLikeService {

    private final BrandLikeRepository brandLikeRepository;

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
