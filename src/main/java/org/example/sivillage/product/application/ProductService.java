package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.domain.ProductOption;
import org.example.sivillage.product.infrastructure.ProductLikeRepository;
import org.example.sivillage.product.infrastructure.ProductOptionRepository;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;
import org.example.sivillage.product.dto.out.GetProductDetailsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductLikeService productLikeService;
    private final BrandRepository brandRepository;
    private final ProductLikeRepository productLikeRepository;

    public void addProduct(CreateProductRequestVo request) {
        Brand brand = brandRepository.findByBrandEngName(request.getBrandEngName())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        String productUuid = UUID.randomUUID().toString();

        Product product = Product.createProduct(request, brand, productUuid);
        productRepository.save(product);

        ProductOption productOption = ProductOption.createProductOption(request, product);
        productOptionRepository.save(productOption);

    }

    @Transactional(readOnly = true)
    public GetProductDetailsResponseDto getProductDetail(String productUuid, String memberUuid) {
        Product product = productRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        ProductOption productOption = productOptionRepository.findByProduct(product)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_OPTION_NOT_FOUND));

        Integer likesCount = productLikeService.countLikesForProduct(productUuid);

        boolean isLiked = productLikeRepository.findIsLikedByProductUuidAndMemberUuid(productUuid, memberUuid)
                .orElse(false);
        return GetProductDetailsResponseDto.toDto(product, productOption, likesCount, isLiked);
    }

//    @Transactional(readOnly = true)
//    public GetProductSizeListResponse getProductSizeList(String productName) {
//        Product product = productRepository.findByName(productName)
//               .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));
//
//
//
////        return GetProductSizeListResponse.toDto(product.getProductOptions());
////    }
}
