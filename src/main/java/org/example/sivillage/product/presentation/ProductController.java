package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.CustomUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.member.vo.out.GetProductCodeListResponseVo;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.product.dto.in.CreateProductRequestDto;
import org.example.sivillage.product.dto.out.GetProductBriefInfoResponseDto;
import org.example.sivillage.product.dto.out.GetProductDetailsResponseDto;
import org.example.sivillage.product.dto.out.GetProductCodeListResponseDto;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;
import org.example.sivillage.product.vo.out.GetProductBriefInfoResponseVo;
import org.example.sivillage.product.vo.out.GetProductDetailsResponseVo;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상품 관리 API", description = "상품 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final ProductLikeService productLikeService;
    private final ModelMapper mapper;

    @Operation(summary = "상품 생성", description = """

    """)
    @PostMapping("/")
    public BaseResponse<Void> addProduct(@Valid @RequestBody CreateProductRequestVo createProductRequestVo) {
        CreateProductRequestDto request = mapper.map(createProductRequestVo, CreateProductRequestDto.class);
        productService.addProduct(request);
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 상세 정보 조회", description = "")
    @GetMapping("/details/{productUuid}")
    public BaseResponse<GetProductDetailsResponseVo> getProductDetail(@PathVariable String productUuid, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        GetProductDetailsResponseDto responseDto = productService.getProductDetail(productUuid, memberUuid);
        GetProductDetailsResponseVo response = mapper.map(responseDto, GetProductDetailsResponseVo.class);
        return new BaseResponse<>(response);
    }

    @Operation(summary = "상품 리스트에 대한 productCode 리스트 조회", description = """
            상품 리스트에 대한 productCode들을 받아오고, 단일 상품 간략 정보 조회하는 api로 해당 productCode값을 전달해서 요청하면 됨.
            """)
    @GetMapping("/uuid")
    public BaseResponse<GetProductCodeListResponseVo> getProductCodeList() {
        GetProductCodeListResponseDto responseDto = productService.getProductCodeList();
        GetProductCodeListResponseVo response = mapper.map(responseDto, GetProductCodeListResponseVo.class);
        return new BaseResponse<>(response);
    }

    @Operation(summary = "단일 상품에 대한 간략 정보 조회", description = """
        '상품 리스트에 대한 Uuid 조회' api로 얻어낸 Uuid 정보를 전달하고, 이 api로 받아온 값을 상품 리스트 렌더링에 활용.
        이미 프론트에서 받아온 정보는 캐싱처리
        """)
    @GetMapping("/brief/{productCode}")
    public BaseResponse<GetProductBriefInfoResponseVo> getProductBriefInfo(@PathVariable String productCode, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        GetProductBriefInfoResponseDto responseDto = productService.getProductBriefInfo(productCode, memberUuid);
        GetProductBriefInfoResponseVo response = mapper.map(responseDto, GetProductBriefInfoResponseVo.class);
        return new BaseResponse<>(response);
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
