package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.domain.ProductOption;
import org.example.sivillage.product.infrastructure.ProductOptionRepository;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.product.vo.CreateProductRequest;
import org.example.sivillage.product.vo.GetProductDetailsResponse;
import org.example.sivillage.global.error.CustomException;
import org.example.sivillage.global.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final BrandService brandService;
    private final ProductLikeService productLikeService;

    public void createProduct(CreateProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_PRODUCT);
        }

        /** Brand 정보가 DB에 있는지 확인
            Brand 정보가 없으면 새로 생성
            Brand 정보가 있으면 그 객체를 받아오기
         **/
        Brand brand = brandService.createBrand(request.getBrand());

        Product product = Product.createProduct(request, brand);
        productRepository.save(product);

        ProductOption productOption = ProductOption.createProductOption(request, product);
        productOptionRepository.save(productOption);

    }

    @Transactional(readOnly = true)
    public GetProductDetailsResponse getProductDetails(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        ProductOption productOption = productOptionRepository.findByProduct(product)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_OPTION_NOT_FOUND));

        Long likesCount = productLikeService.countLikesForProduct(productId);

        return GetProductDetailsResponse.toDto(product, productOption, likesCount);
    }

//    @Transactional(readOnly = true)
//    public GetProductSizeListResponse getProductSizeList(String productName) {
//        Product product = productRepository.findByName(productName)
//               .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
//
//
//
////        return GetProductSizeListResponse.toDto(product.getProductOptions());
////    }
}
