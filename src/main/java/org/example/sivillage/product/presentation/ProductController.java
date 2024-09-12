package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.product.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.product.dto.out.GetProductImageUrlListResponseDto;
import org.example.sivillage.product.dto.out.GetProductOptionListResponseDto;
import org.example.sivillage.product.vo.in.CreateProductImageListRequestDto;
import org.example.sivillage.product.vo.in.CreateProductImageListRequestVo;
import org.example.sivillage.product.vo.in.CreateProductOptionRequestVo;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;
import org.example.sivillage.product.vo.out.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품 관리 API", description = "상품 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    /**
     * 1. 상품 등록
     * 2. 상품 옵션 등록
     * 3. 상품 이미지 등록
     * 4. 상품 간략 정보 조회
     * 5. 상품 옵션 정보 조회
     * 6. 상품 상세 정보 조회
     * 7. 상품 썸네일 URL 조회
     * 8. 상품 이미지 URL 리스트 조회
     */

    @Operation(summary = "상품 등록")
    @PostMapping("/")
    public BaseResponse<Void> addProduct(@Valid @RequestBody CreateProductRequestVo createProductRequestVo) {

        productService.addProduct(CreateProductRequestDto.from(createProductRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 옵션 등록")
    @PostMapping("/option")
    public BaseResponse<Void> addProductOption(@Valid @RequestBody CreateProductOptionRequestVo createProductOptionRequestVo) {

        productService.addProductOption(CreateProductOptionRequestDto.from(createProductOptionRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 이미지 리스트 등록")
    @PostMapping("/image")
    public BaseResponse<Void> addProductImageList(@RequestBody List<CreateProductImageListRequestVo> createProductImageListRequestVo) {

        List<CreateProductImageListRequestDto> createProductImageListRequestDtoList =
                createProductImageListRequestVo.stream()
                        .map(CreateProductImageListRequestDto::from)
                        .toList();

        productService.addProductImageList(createProductImageListRequestDtoList);
        return new BaseResponse<>();
    }

    @Operation(summary = "단일 상품에 대한 간략 정보 조회", description = "")
    @GetMapping("/brief/{productCode}")
    public BaseResponse<GetProductBriefInfoResponseVo> getProductBriefInfo(@PathVariable String productCode) {

        return new BaseResponse<>(
                productService.getProductBriefInfo(productCode).toVo()
        );
    }

    @Operation(summary = "상품에 등록된 옵션 정보 리스트 조회")
    @GetMapping("/details/option/{productCode}")
    public BaseResponse<List<GetProductOptionListResponseVo>> getProductOptionList(@PathVariable String productCode) {

        return new BaseResponse<>(
                productService.getProductOptionList(productCode).stream()
                        .map(GetProductOptionListResponseDto::toVo)
                        .toList()
        );
    }

    @Operation(summary = "상품 상세 정보 조회", description = "")
    @GetMapping("/details/{productCode}")
    public BaseResponse<GetProductDetailsResponseVo> getProductDetail(@PathVariable String productCode) {

        return new BaseResponse<>(
                productService.getProductDetail(productCode).toVo()
        );
    }

    @Operation(summary = "상품 썸네일 URL 조회", description = """
        상품 썸네일 URL을 조회하는 API
        """)
    @GetMapping("/brief/thumbnail/{productCode}")
    public BaseResponse<GetProductThumbnailUrlResponseVo> getProductThumbnailUrl(@PathVariable String productCode) {

        return new BaseResponse<>(
                productService.getProductThumbnailUrl(productCode).toVo()
        );
    }

    @Operation(summary = "상품 이미지 URL 리스트 조회", description = """
        상품 이미지 URL 리스트를 조회하는 API
        """)
    @GetMapping("/details/images/{productCode}")
    public BaseResponse<List<GetProductImageUrlListResponseVo>> getProductImageUrlList(@PathVariable String productCode) {

        return new BaseResponse<>(
                productService.getProductImageUrlList(productCode).stream()
                        .map(GetProductImageUrlListResponseDto::toVo)
                        .toList()
        );
    }




//    @PostMapping(value = "/from-csv", consumes = "multipart/form-data")
//    public BaseResponse<Void> addProductsFromCsv(@RequestParam("file") MultipartFile file) {
//        productService.addProductsFromCsv(file);
//        return new BaseResponse<>();
//    }
//    @Operation(summary = "특정 물품의 전체 카테고리 리스트 반환", description = "test용")
//    @GetMapping("/category-path/{productUuid}")
//    public BaseResponse<GetCategoryPathResponseVo> getCategoryPath(@PathVariable String productUuid) {
//        GetCategoryPathResponseDto responseDto = productService.getCategoryPath(productUuid);
//        GetCategoryPathResponseVo response = mapper.map(responseDto, GetCategoryPathResponseVo.class);
//        return new BaseResponse<>(response);
//    }

}
