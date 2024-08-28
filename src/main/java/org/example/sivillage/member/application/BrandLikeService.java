package org.example.sivillage.member.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.brand.infrastructure.BrandLikeRepository;
import org.example.sivillage.member.domain.BrandLike;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandLikeService {
    private final BrandLikeRepository brandLikeRepository;

    public void toggleBrandLike(Long brandId, String memberUuid) {
        BrandLike brandLike = brandLikeRepository.findByBrandIdAndMemberUuid(brandId, memberUuid)
                .orElse(BrandLike.createLikedBrand(brandId, memberUuid));

        // 현재 상태를 토글 (좋아요 -> 싫어요, 싫어요 -> 좋아요)
        brandLike.toggleLike();

        brandLikeRepository.save(brandLike);
    }

}
