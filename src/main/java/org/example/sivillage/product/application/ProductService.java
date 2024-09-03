package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.domain.ProductImage;
import org.example.sivillage.product.domain.ProductOption;
import org.example.sivillage.product.dto.out.*;
import org.example.sivillage.product.infrastructure.ProductImageRepository;
import org.example.sivillage.product.infrastructure.ProductLikeRepository;
import org.example.sivillage.product.infrastructure.ProductOptionRepository;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    public void addProduct(CreateProductRequestVo request) {
        Brand brand = brandRepository.findByBrandEngName(request.getBrandEngName())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        Category category = categoryRepository.findByCategoryCode(request.getCategoryCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        Product product = createAndSaveProduct(request, brand, category);
        saveProductImages(request.getProductImageUrls(), product);
        createAndSaveProductOption(request, product);

    }

    private void createAndSaveProductOption(CreateProductRequestVo request, Product product) {
        ProductOption productOption = ProductOption.createProductOption(request, product);
        productOptionRepository.save(productOption);
    }

    private void saveProductImages(List<String> productImageUrls, Product product) {
        List<ProductImage> productImageUrl = productImageUrls.stream()
                .map(url -> ProductImage.createProductImage(url, product))
                .collect(Collectors.toList());
        productImageRepository.saveAll(productImageUrl);
    }

    private Product createAndSaveProduct(CreateProductRequestVo request, Brand brand, Category category) {
        String productUuid = UUID.randomUUID().toString();

        Product product = Product.createProduct(request, brand, productUuid, category);
        productRepository.save(product);
        return product;
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

        List<String> productImageUrls = productImageRepository.findByProduct(product)
                .stream()
                .map(ProductImage::getProductImageUrl)
                .collect(Collectors.toList());

        return GetProductDetailsResponseDto.toDto(product, productOption, likesCount, isLiked, productImageUrls);
    }

    @Transactional(readOnly = true)
    public GetProductsUuidListResponseDto getProductsUuid() {
        // TODO: 카테고리 id를 추가로 받아서 필터링하여 조회
        // TODO: 상품 옵션에 따라 필터링하여 조회
        // 일단 테스트 버전은 findAll로 다 가져오기
        List<String> productUuids = productRepository.findAllProductUuids();

        List<GetProductsUuidResponseDto> getProductsUuidResponseDtos = productUuids.stream()
                .map(GetProductsUuidResponseDto::new)
                .collect(Collectors.toList());

        return new GetProductsUuidListResponseDto(getProductsUuidResponseDtos);
    }

    public GetProductBriefInfoResponseDto getProductBriefInfo(String productUuid, String memberUuid) {
        Product product = productRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        // TODO: 비회원인 경우 isLiked 무조건 false로 반환하기
        boolean isLiked = productLikeRepository.findIsLikedByProductUuidAndMemberUuid(productUuid, memberUuid)
                .orElse(false);

        // 기존 productImage 중 첫번째 이미지를 썸네일로 사용
        String productThumbnailUrl = getProductThumbnailUrl(product);

        return GetProductBriefInfoResponseDto.toDto(product, isLiked, productThumbnailUrl);
    }

    public GetCategoryPathResponseDto getCategoryPath(String productUuid) {
        Product product = productRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        Category category = product.getCategory();
        List<String> categoryPath = new ArrayList<>();

        while (category != null) {
            categoryPath.add(category.getCategoryName());
            category = category.getParent(); // 부모 카테고리로 이동
        }

        // 상위 카테고리부터 하위 카테고리 순으로 정렬
        Collections.reverse(categoryPath);

        return new GetCategoryPathResponseDto(categoryPath);
    }

//    public String getSecondLevelCategoryName(String productUuid) {
//        Product product = productRepository.findByProductUuid(productUuid)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));
//
//        // 해당 물품의 카테고리를 가져옴
//        Category category = product.getCategory();
//
//        // depth가 1인 카테고리 탐색
//        return findSecondLevelCategory(category)
//                .map(Category::getCategoryName)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));
//    }

    private String getProductThumbnailUrl(Product product) {
        return productImageRepository.findByProduct(product)
                .stream()
                .map(ProductImage::getProductImageUrl)
                .findFirst()
                .orElse(null);
    }

//    private Optional<Category> findSecondLevelCategory(Category category) {
//        // 최상위 카테고리를 찾을 때까지 부모를 탐색
//        while (category != null && category.getDepth() != 1) {
//            category = category.getParent();
//        }
//        return Optional.ofNullable(category);
//    }
}
