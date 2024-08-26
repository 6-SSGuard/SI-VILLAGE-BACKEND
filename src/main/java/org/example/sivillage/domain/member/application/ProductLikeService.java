package org.example.sivillage.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.domain.member.domain.Member;
import org.example.sivillage.domain.member.domain.ProductLike;
import org.example.sivillage.domain.member.infrastructure.MemberRepository;
import org.example.sivillage.domain.product.domain.Product;
import org.example.sivillage.domain.product.infrastructure.ProductLikeRepository;
import org.example.sivillage.domain.product.infrastructure.ProductRepository;
import org.example.sivillage.domain.product.vo.LikeProductRequest;
import org.example.sivillage.domain.product.vo.UnLikeProductRequest;
import org.example.sivillage.global.error.CustomException;
import org.example.sivillage.global.error.ErrorCode;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLikeService {

    private final ProductLikeRepository productLikeRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;


    @CacheEvict(value = "procductLikes", key = "#product.id + '-' + #member.id")
    public void unlikeProduct(UnLikeProductRequest request) {
        Product product = getProduct(request.getProductId());
        Member member = getMember(request.getMemberId());
        productLikeRepository.deleteByProductAndMember(product, member);
    }

    @Cacheable(value = "procductLikes", key = "#product.id + '-' + #member.id")
    public boolean isProductLikedByMember(Long productId, Long memberId) {
        Product product = getProduct(productId);
        Member member = getMember(memberId);
        return productLikeRepository.existsByProductAndMember(product, member);
    }

    @CachePut(value = "procductLikes", key = "#product.id + '-' + #member.id")
    public void likeProduct(LikeProductRequest request) {
        Product product = getProduct(request.getProductId());
        Member member = getMember(request.getMemberId());

        ProductLike productLike = ProductLike.createProductLike(product, member);
        productLikeRepository.save(productLike);
    }

    public long countLikesForProduct(Long productId) {
        Product product = getProduct(productId);
        return productLikeRepository.countByProduct(product);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

}
