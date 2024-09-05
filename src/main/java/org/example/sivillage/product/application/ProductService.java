package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.admin.infrastructure.CategoryProductRepository;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.domain.ProductImage;
import org.example.sivillage.product.domain.ProductOption;
import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.product.dto.out.GetProductBriefInfoResponseDto;
import org.example.sivillage.product.dto.out.GetProductDetailsResponseDto;
import org.example.sivillage.product.dto.out.GetProductsUuidListResponseDto;
import org.example.sivillage.product.dto.out.GetProductsUuidResponseDto;
import org.example.sivillage.product.infrastructure.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {
    private static final Random RANDOM = new Random();

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductLikeService productLikeService;
    private final BrandRepository brandRepository;
    private final ProductLikeRepository productLikeRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryProductRepository categoryProductRepository;
    private final BrandProductRepository brandProductRepository;


    public void addProduct(CreateProductRequestDto request) {
        Brand brand = brandRepository.findByBrandEngName(request.getBrandEngName())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        Category category = categoryRepository.findByCategoryCode(request.getCategoryCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        Product product = createAndSaveProduct(request, brand.getBrandId(), category.getCategoryCode());
        saveProductImages(request.getProductImageUrls(), product.getProductCode());

        ProductOption productOption = ProductOption.createProductOption(request, product.getProductCode());
        productOptionRepository.save(productOption);
    }

    private Product createAndSaveProduct(CreateProductRequestDto request, Long brandId, String categoryCode) {
        String productCode = UUID.randomUUID().toString();

        Product product = Product.createProduct(request, brandId, productCode, categoryCode);
        productRepository.save(product);
        return product;
    }

    // 썸네일 이미지와 일반 이미지를 구분하여  저장
    private void saveProductImages(List<CreateProductRequestDto.ProductImageDto> productImageUrls, String productCode) {
        productImageRepository.saveAll(
                productImageUrls.stream()
                        .map(imageDto -> ProductImage.createProductImage(imageDto, productCode))
                        .collect(Collectors.toList())
        );
    }


    @Transactional(readOnly = true)
    public GetProductDetailsResponseDto getProductDetail(String productCode, String memberUuid) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        ProductOption productOption = productOptionRepository.findByProductCode(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_OPTION_NOT_FOUND));

        Integer likesCount = productLikeService.countLikesForProduct(productCode);

        boolean isLiked = productLikeRepository.findIsLikedByProductUuidAndMemberUuid(productCode, memberUuid)
                .orElse(false);

        return GetProductDetailsResponseDto.toDto(product, productOption, likesCount, isLiked);
    }

    @Transactional(readOnly = true)
    public GetProductsUuidListResponseDto getProductsUuid() {
        // TODO: 카테고리 id를 추가로 받아서 필터링하여 조회
        // TODO: 상품 옵션에 따라 필터링하여 조회
        // 일단 테스트 버전은 findAll로 다 가져오기
        List<String> productUuids = productRepository.findAllProductCode();

        List<GetProductsUuidResponseDto> getProductsUuidResponseDtos = productUuids.stream()
                .map(GetProductsUuidResponseDto::new)
                .collect(Collectors.toList());

        return new GetProductsUuidListResponseDto(getProductsUuidResponseDtos);
    }

    public GetProductBriefInfoResponseDto getProductBriefInfo(String productUuid, String memberUuid) {
        Product product = productRepository.findByProductCode(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        // TODO: 비회원인 경우 isLiked 무조건 false로 반환하기
        boolean isLiked = productLikeRepository.findIsLikedByProductUuidAndMemberUuid(productUuid, memberUuid)
                .orElse(false);

        // 기존 productImage 중 첫번째 이미지를 썸네일로 사용
        String productThumbnailUrl = getProductThumbnailUrl(product.getProductCode());

        return GetProductBriefInfoResponseDto.toDto(product, isLiked, productThumbnailUrl);
    }

    private String getProductThumbnailUrl(String productCode) {
        return productImageRepository.findByProductCode(productCode)
                .stream()
                .map(ProductImage::getProductImageUrl)
                .findFirst()
                .orElse(null);
    }

//    public GetCategoryPathResponseDto getCategoryPath(String productUuid) {
//        Product product = productRepository.findByProductUuid(productUuid)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));
//
//        Category category = product.getCategory();
//        List<String> categoryPath = new ArrayList<>();
//
//        while (category != null) {
//            categoryPath.add(category.getCategoryName());
//            category = category.getParent(); // 부모 카테고리로 이동
//        }
//
//        // 상위 카테고리부터 하위 카테고리 순으로 정렬
//        Collections.reverse(categoryPath);
//
//        return new GetCategoryPathResponseDto(categoryPath);
//    }

//    public void addProductsFromCsv(MultipartFile file) {
//        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
//            throw new BaseException(BaseResponseStatus.INVALID_FILE_FORMAT);
//        }
//
//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
//
//            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
//            records.forEach(this::parseRecordToProductRequest);
//
//        } catch (IOException e) {
//            log.error("파일을 읽는 중 오류 발생: {}", e.getMessage(), e);
//            throw new BaseException(BaseResponseStatus.FILE_PARSE_FAILED);
//        } catch (Exception e) {
//            log.error("CSV 파싱 중 오류 발생: {}", e.getMessage(), e);
//            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
////    public String getSecondLevelCategoryName(String productUuid) {
////        Product product = productRepository.findByProductUuid(productUuid)
////                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));
////
////        // 해당 물품의 카테고리를 가져옴
////        Category category = product.getCategoryCode();
////
////        // depth가 1인 카테고리 탐색
////        return findSecondLevelCategory(category)
////                .map(Category::getCategoryName)
////                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));
////    }
//
//    private String getProductThumbnailUrl(String productCode) {
//        return productImageRepository.findByProductCode(productCode)
//                .stream()
//                .map(ProductImage::getProductImageUrl)
//                .findFirst()
//                .orElse(null);
//    }
//
//    private Optional<Category> findSecondLevelCategory(Category category) {
//        // 최상위 카테고리를 찾을 때까지 부모를 탐색
//        while (category != null && category.getDepth() != 1) {
//            category = category.getParent();
//        }
//        return Optional.ofNullable(category);
//    }
//
//    private void parseRecordToProductRequest(CSVRecord record) {
//        try {
//            // 카테고리 이름 추출
//            String categoryName = extractCategoryName(record);
//            Category category = findOrCreateCategory(categoryName);
//
//            // 브랜드 ID 처리
//            Long brandId = findOrCreateBrand(record.get("brand"));
//
//            // 상품 코드 생성
//            String productCode = UuidGenerator.createProductCode();
//
//            // 상품 정보 처리
//            handleProduct(record, category, brandId, productCode);
//
//            // 상품 옵션 처리 (의류/신발 등)
//            handleProductOptions(record, productCode);
//
//        } catch (Exception e) {
//            log.error("레코드 처리 중 오류 발생: {}", e.getMessage(), e);
//            throw new BaseException(BaseResponseStatus.FILE_PARSE_FAILED);
//        }
//    }
//
//
//
//    // 카테고리 이름 추출
//    private String extractCategoryName(CSVRecord record) {
//        String topCategoryName = record.get("top_category_name");
//        String middleCategoryName = record.get("middle_category_name");
//        String bottomCategoryName = record.get("bottom_category_name");
//        String subCategoryName = record.get("sub_category_name");
//
//        return (subCategoryName == null || subCategoryName.isEmpty()) ? bottomCategoryName : subCategoryName;
//    }
//
//    // 카테고리 조회 및 없을 시 생성
//    private Category findOrCreateCategory(String categoryName) {
//        return categoryCache.computeIfAbsent(categoryName, name -> categoryRepository.findByCategoryName(name)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND)));
//    }
//
//    // 브랜드 조회 및 없을 시 생성
//    private Long findOrCreateBrand(String brandEngName) {
//        return brandCache.computeIfAbsent(brandEngName, name -> {
//            Brand brand = brandRepository.findByBrandEngName(name)
//                    .orElseGet(() -> brandRepository.save(
//                            Brand.builder()
//                                    .brandEngName(name)
//                                    .brandKorName("테스트")
//                                    .build()
//                    ));
//            return brand.getBrandId();
//        });
//    }
//
//    // 상품 정보 처리
//    private void handleProduct(CSVRecord record, Category category, Long brandId, String productCode) {
//        // 가격 파싱
//        String priceString = record.get("price");
//        Integer price = parsePrice(priceString);
//
//        // 상세 설명 및 이미지 처리
//        String detailContent = record.get("image_src");
//        List<String> productImageUrls = Collections.singletonList(detailContent);
//
//        // BrandProduct 저장
//        BrandProduct brandProduct = BrandProduct.builder()
//                .brandId(brandId)
//                .productCode(productCode)
//                .build();
//        brandProductRepository.save(brandProduct);
//
//        // CategoryProduct 저장
//        CategoryProduct categoryProduct = CategoryProduct.builder()
//                .categoryCode(category.getCategoryCode())
//                .productCode(productCode)
//                .build();
//        categoryProductRepository.save(categoryProduct);
//
//        // ProductImage 저장
//        productImageUrls.forEach(url -> {
//            ProductImage productImage = ProductImage.builder()
//                    .productCode(productCode)
//                    .productImageUrl(url)
//                    .build();
//            productImageRepository.save(productImage);
//        });
//
//        // Product 저장
//        CreateProductFromCsvRequestDto createProductDto = CreateProductFromCsvRequestDto.builder()
//                .productName(record.get("name"))
//                .productCode(productCode)
//                .detailContent(detailContent)
//                .price(price)
//                .build();
//        Product product = Product.createProductFromCsv(createProductDto);
//        productRepository.save(product);
//    }
//
//    // 상품 가격 파싱
//    private Integer parsePrice(String priceString) {
//        if (priceString == null || priceString.trim().isEmpty()) {
//            log.error("가격 필드가 비어있습니다.");
//            return 0;
//        }
//
//        try {
//            return Integer.parseInt(priceString.replace(",", ""));
//        } catch (NumberFormatException e) {
//            log.error("가격 필드가 잘못되었습니다: {}", priceString);
//            return 0;
//        }
//    }
//
//
//    // 의류 및 신발 옵션 처리
//    private void handleProductOptions(CSVRecord record, String productCode) {
//        String topCategoryName = record.get("top_category_name");
//        String middleCategoryName = record.get("middle_category_name");
//        String bottomCategoryName = record.get("bottom_category_name");
//
//        boolean isClothing = isClothingCategory(topCategoryName, middleCategoryName, bottomCategoryName);
//        boolean isShoes = isShoesCategory(topCategoryName, middleCategoryName);
//
//        Color randomColor = getRandomColor();
//        String volume = record.get("volume");
//
//        if (isClothing) {
//            Size size = getRandomSize();
//            ProductOption option = ProductOption.builder()
//                    .productCode(productCode)
//                    .color(randomColor)
//                    .stock(getRandomStock())
//                    .size(size)
//                    .volume(volume)
//                    .build();
//            productOptionRepository.save(option);
//        } else if (isShoes) {
//            ShoeSize shoeSize = getRandomShoeSize();
//            ProductOption option = ProductOption.builder()
//                    .productCode(productCode)
//                    .color(randomColor)
//                    .stock(getRandomStock())
//                    .shoeSize(shoeSize)
//                    .volume(volume)
//                    .build();
//            productOptionRepository.save(option);
//        }
//    }
//
//    // 랜덤 색상 및 사이즈 반환
//    private Color getRandomColor() {
//        Color[] colors = Color.values();
//        return colors[RANDOM.nextInt(colors.length)];
//    }
//    private Size getRandomSize() {
//        Size[] sizes = Size.values();
//        return sizes[RANDOM.nextInt(sizes.length)];
//    }
//    private ShoeSize getRandomShoeSize() {
//        ShoeSize[] shoeSizes = ShoeSize.values();
//        return shoeSizes[RANDOM.nextInt(shoeSizes.length)];
//    }
//
//    private List<Size> getRandomSizes() {
//        Size[] sizes = Size.values();
//        return Stream.of(sizes)
//                .filter(size -> RANDOM.nextBoolean())
//                .collect(Collectors.toList());
//    }
//
//    private List<ShoeSize> getRandomShoeSizes() {
//        ShoeSize[] sizes = ShoeSize.values();
//        return Stream.of(sizes)
//                .filter(size -> RANDOM.nextBoolean())
//                .collect(Collectors.toList());
//    }
//
//    private int getRandomStock() {
//        return RANDOM.nextInt(100) + 1; // 랜덤 재고 수량
//    }
//
//    private boolean isClothingCategory(String topCategoryName, String middleCategoryName, String bottomCategoryName) {
//        return (topCategoryName.equals("남성의류") || topCategoryName.equals("여성의류")) ||
//                (middleCategoryName.equals("여성 골프웨어") || middleCategoryName.equals("남성 골프웨어")) ||
//                List.of("남성의류", "여성의류", "한복/파티복/코스튬", "상/하의 세트", "니트/가디건/조끼", "티셔츠",
//                        "우주복/바디슈트/점프슈트", "아우터", "맨투맨/후디", "데님팬츠", "팬츠", "셔츠",
//                        "스포츠웨어/수영복", "원피스/스커트/점프슈트", "블라우스/셔츠",
//                        "실내복/잠옷/언더웨어", "원피스/스커트").contains(bottomCategoryName);
//    }
//
//    private boolean isShoesCategory(String topCategoryName, String bottomCategoryName) {
//        return topCategoryName.equals("슈즈") &&
//                List.of("러닝화", "신발", "거실화", "골프화").contains(bottomCategoryName);
//    }
}
