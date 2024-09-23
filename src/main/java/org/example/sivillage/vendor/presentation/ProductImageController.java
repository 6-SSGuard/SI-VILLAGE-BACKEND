package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.product.vo.in.CreateProductImageListRequestVo;
import org.example.sivillage.product.vo.out.GetProductImageUrlListResponseVo;
import org.example.sivillage.product.vo.out.GetProductThumbnailUrlResponseVo;
import org.example.sivillage.vendor.application.ProductImageService;
import org.example.sivillage.vendor.dto.in.CreateProductImageListRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductImageUrlListResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
    @RequestMapping("/api/vendor/product/image")
@Tag(name = "벤더의 상품 관리 API")
public class ProductImageController {

    private final ProductImageService productImageService;

    @Operation(summary = "상품 이미지 리스트 등록")
    @PostMapping
    public BaseResponse<Void> addProductImageList(@RequestBody List<CreateProductImageListRequestVo> createProductImageListRequestVo) {

        List<CreateProductImageListRequestDto> createProductImageListRequestDtoList =
                createProductImageListRequestVo.stream()
                        .map(CreateProductImageListRequestDto::from)
                        .toList();

        productImageService.addProductImageList(createProductImageListRequestDtoList);
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 이미지 리스트 수정")
    @PutMapping
    public BaseResponse<Void> updateProductImageList(@RequestBody List<CreateProductImageListRequestVo> createProductImageListRequestVo) {

        List<CreateProductImageListRequestDto> createProductImageListRequestDtoList =
                createProductImageListRequestVo.stream()
                        .map(CreateProductImageListRequestDto::from)
                        .toList();

        productImageService.updateProductImageList(createProductImageListRequestDtoList);
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 썸네일 URL 조회", description = """
        상품 썸네일 URL을 조회하는 API
        """)
    @GetMapping("/brief/thumbnail/{productCode}")
    public BaseResponse<GetProductThumbnailUrlResponseVo> getProductThumbnailUrl(@PathVariable String productCode) {

        return new BaseResponse<>(
                productImageService.getProductThumbnailUrl(productCode).toVo()
        );
    }

    @Operation(summary = "상품 이미지 URL 리스트 조회", description = """
        상품 이미지 URL 리스트를 조회하는 API
        """)
    @GetMapping("/details/images/{productCode}")
    public BaseResponse<List<GetProductImageUrlListResponseVo>> getProductImageUrlList(@PathVariable String productCode) {

        return new BaseResponse<>(
                productImageService.getProductImageUrlList(productCode).stream()
                        .map(GetProductImageUrlListResponseDto::toVo)
                        .toList()
        );
    }
}
