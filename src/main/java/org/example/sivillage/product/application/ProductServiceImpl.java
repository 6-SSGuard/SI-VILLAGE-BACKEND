package org.example.sivillage.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.product.domain.BrandProduct;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.dto.in.ChangeProductRequestDto;
import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.product.dto.out.CreateProductResponseDto;
import org.example.sivillage.product.dto.out.GetProductBriefInfoResponseDto;
import org.example.sivillage.product.dto.out.GetProductDetailsResponseDto;
import org.example.sivillage.product.infrastructure.BrandProductRepository;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.vendor.domain.ProductCategoryList;
import org.example.sivillage.vendor.domain.ProductImage;
import org.example.sivillage.vendor.domain.ProductOptionList;
import org.example.sivillage.vendor.infrastructure.ProductCategoryListRepository;
import org.example.sivillage.vendor.infrastructure.ProductImageRepository;
import org.example.sivillage.vendor.infrastructure.ProductOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final BrandProductRepository brandProductRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryListRepository productCategoryListRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionListRepository;

    /**
     * 1. 상품 등록
     * 4. 상품 간략 정보 조회
     * 6. 상품 상세 정보 조회
     */

    /**
     * 1. 상품 등록
     * @param createProductRequestDto 상품 등록 요청 DTO
     */
    public CreateProductResponseDto addProduct(CreateProductRequestDto createProductRequestDto) {

        Product product = productRepository.save(createProductRequestDto.toEntity());
        brandProductRepository.save(createProductRequestDto.toEntity(
                createProductRequestDto.getBrandId(), product.getProductCode()));
        log.debug("상품 등록 완료: {}", product.getProductCode());
        return CreateProductResponseDto.from(product.getProductCode());
    }


    /**
     * 4. 상품 간략 정보 조회
     * @param productCode 상품 코드
     * @return GetProductBriefInfoResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public GetProductBriefInfoResponseDto getProductBriefInfo(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        Brand brand = brandRepository.findById(product.getBrandId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        return GetProductBriefInfoResponseDto.of(product, brand);
    }



    /**
     * 6. 상품 상세 정보 조회
     * @param productCode 상품 코드
     * @return GetProductDetailsResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public GetProductDetailsResponseDto getProductDetail(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        Brand brand = brandRepository.findById(product.getBrandId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        return GetProductDetailsResponseDto.of(product, brand);
    }

    @Override
    public void changeProduct(ChangeProductRequestDto changeProductRequestDto) {
        Long productId = productRepository.findByProductCode(changeProductRequestDto.getProductCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND)).getId();

        Brand brand = brandRepository.findById(changeProductRequestDto.getBrandId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        Product product = productRepository.save(changeProductRequestDto.changeProduct(productId));

        brandProductRepository.save(changeProductRequestDto.changeBrandProduct(
                brand, product.getProductCode()));

    }

    @Override
    public void addProductByCsv(MultipartFile file) {

        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
            throw new BaseException(BaseResponseStatus.INVALID_FILE_FORMAT);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            records.forEach(this::parseProductCsvRecord);

        } catch (IOException e) {
            log.error("파일을 읽는 중 오류 발생: {}", e.getMessage(), e);
            throw new BaseException(BaseResponseStatus.FILE_PARSE_FAILED);
        } catch (Exception e) {
            log.error("CSV 파싱 중 오류 발생: {}", e.getMessage(), e);
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private void parseProductCsvRecord(CSVRecord record) {
        try {
            String productName = record.get("productName");
            Integer price = Integer.parseInt(record.get("price"));
            Long colorId = Long.parseLong(record.get("colorId"));
            String productCode = record.get("productCode");

            String topCategoryCode = categoryRepository.findFirstByCategoryNameOrderByIdAsc(record.get("topCategoryName"))
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.TOP_CATEGORY_NOT_FOUND)).getCategoryCode();


            String middleCategoryCode = categoryRepository.findFirstByCategoryNameOrderByIdAsc(record.get("middleCategoryName"))
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.MIDDLE_CATEGORY_NOT_FOUND)).getCategoryCode();

            String bottomCategoryCode = categoryRepository.findFirstByCategoryNameOrderByIdAsc(record.get("bottomCategoryName"))
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.BOTTOM_CATEGORY_NOT_FOUND)).getCategoryCode();

            String subCategoryCode = "";
            if (record.get("subCategoryName").isEmpty() || record.get("subCategoryName").equals("null")) {
                subCategoryCode = "";
            }
            else {
                subCategoryCode = categoryRepository.findFirstByCategoryNameOrderByIdAsc(record.get("subCategoryName"))
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.SUB_CATEGORY_NOT_FOUND)).getCategoryCode();
            }

            String imageUrl = record.get("imageUrl");
            String brandName = record.get("brandName");
            String volume = record.get("volume");


            // 브랜드 ID 처리
            Long brandId = brandRepository.findByBrandEngName(brandName)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND))
                    .getId();

            Product product = Product.builder()
                    .productName(productName)
                    .price(price)
                    .colorId(colorId)
                    .productCode(productCode)
                    .detailContent(imageUrl)
                    .brandId(brandId)
                    .build();

            productRepository.save(product);

            BrandProduct brandProduct = BrandProduct.builder()
                    .brandId(brandId)
                    .productCode(productCode)
                    .build();

            brandProductRepository.save(brandProduct);

            ProductCategoryList productCategoryList = ProductCategoryList.builder()
                    .productCode(productCode)
                    .topCategoryCode(topCategoryCode)
                    .middleCategoryCode(middleCategoryCode)
                    .bottomCategoryCode(bottomCategoryCode)
                    .subCategoryCode(subCategoryCode)
                    .build();

            productCategoryListRepository.save(productCategoryList);

            ProductImage productImage = ProductImage.builder()
                    .productCode(productCode)
                    .productImageUrl(imageUrl)
                    .build();

            productImageRepository.save(productImage);

            ProductOptionList productOptionList = ProductOptionList.builder()
                    .productCode(productCode)
                    .volume(volume)
                    .stock(100)
                    .dangerStock(10)
                    .soldOut(false)
                    .build();

            productOptionListRepository.save(productOptionList);

        } catch (Exception e) {
            log.error("레코드 처리 중 오류 발생: {}", e.getMessage(), e);
            log.error("레코드 처리 중 오류 발생, 문제의 레코드: {}", record.toString(), e);
            throw new BaseException(BaseResponseStatus.FILE_PARSE_FAILED);
        }
    }
}
