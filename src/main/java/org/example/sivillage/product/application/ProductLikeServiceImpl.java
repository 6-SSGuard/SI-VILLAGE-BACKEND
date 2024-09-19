package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.product.domain.ProductLike;
import org.example.sivillage.product.dto.out.GetLikeCountResponseDto;
import org.example.sivillage.product.infrastructure.ProductLikeRepository;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLikeServiceImpl implements ProductLikeService {

    private final ProductLikeRepository productLikeRepository;
    private final ProductRepository productRepository;
//    private final MemberRepository memberRepository;


//    @CacheEvict(value = "procductLikes", key = "#product.productCode + '-' + #member.memberUuid")
//    public void unlikeProduct(UnlikeProductRequestDto request) {
//        Product product = getProduct(request.getProductCode());
//        Member member = getMember(request.getMemberUuid());
//        productLikeRepository.deleteByProductAndMember(product, member);
//    }
//
//    @Cacheable(value = "procductLikes", key = "#product.productCode + '-' + #member.memberUuid")
//    public boolean isProductLikedByMember(String productCode, String memberUuid) {
//        Product product = getProduct(productCode);
//        Member member = getMember(memberUuid);
//        return productLikeRepository.existsByProductAndMember(product, member);
//    }

//    @CachePut(value = "procductLikes", key = "#product.productUuid + '-' + #member.memberUuid")
//    public void likeProduct(LikeProductRequestDto request) {
//        Product product = getProduct(request.getProductCode());
//        Member member = getMember(request.getMemberUuid());
//
//        ProductLike productLike = ProductLike.createProductLike(product, member);
//        productLikeRepository.save(productLike);
//    }
//
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

//    private Product getProduct(String productCode) {
//        return productRepository.findByProductCode(productCode)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));
//    }
//
//    private Member getMember(String memberUuid) {
//        return memberRepository.findByMemberUuid(memberUuid)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND));
//    }

}
