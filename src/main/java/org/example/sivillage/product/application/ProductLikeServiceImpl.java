package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.product.domain.ProductLike;
import org.example.sivillage.product.dto.out.GetLikeCountResponseDto;
import org.example.sivillage.product.infrastructure.ProductLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLikeServiceImpl implements ProductLikeService {

    private final ProductLikeRepository productLikeRepository;


    @Transactional(readOnly = true)
    public GetLikeCountResponseDto getLikeCount(String productCode) {
        return GetLikeCountResponseDto.from(productLikeRepository.countByProductCode(productCode));
    }

    @Override
    public void toggleProductLike(String productCode, String memberUuid) {
        ProductLike productLike = productLikeRepository.findByProductCodeAndMemberUuid(productCode, memberUuid)
                .orElse(ProductLike.toEntity(productCode, memberUuid));

        // 현재 상태를 토글 (좋아요 -> 싫어요, 싫어요 -> 좋아요)
        productLike.toggleLike();

        productLikeRepository.save(productLike);
    }

    @Override
    public GetLikeInfoResponseDto getLikeInfo(String productCode, String memberUuid) {
        return productLikeRepository.findByProductCodeAndMemberUuid(productCode, memberUuid)
                .map(GetLikeInfoResponseDto::from)
                .orElse(GetLikeInfoResponseDto.builder()
                        .liked(false)
                        .build());
    }
}
