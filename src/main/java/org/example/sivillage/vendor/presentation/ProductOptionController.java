package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.vendor.application.ProductOptionService;
import org.example.sivillage.vendor.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.vendor.dto.in.UpdateProductOptionRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductOptionListResponseDto;
import org.example.sivillage.vendor.vo.in.CreateProductOptionRequestVo;
import org.example.sivillage.vendor.vo.in.UpdateProductOptionRequestVo;
import org.example.sivillage.vendor.vo.out.GetProductOptionListResponseVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/option")
@Tag(name = "벤더의 상품 관리 API")
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @Operation(summary = "상품 옵션 등록")
    @PostMapping("/vendor")
    public BaseResponse<Void> addProductOptionList(@RequestBody List<CreateProductOptionRequestVo> createProductOptionRequestVo) {

        List<CreateProductOptionRequestDto> createProductOptionRequestDtoList =
                createProductOptionRequestVo.stream()
                        .map(CreateProductOptionRequestDto::from)
                        .toList();

        productOptionService.addProductOptionList(createProductOptionRequestDtoList);
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 옵션 수정")
    @PutMapping("/vendor")
    public BaseResponse<Void> updateProductOptionList(@RequestBody List<UpdateProductOptionRequestVo> updateProductOptionRequestVo) {

        List<UpdateProductOptionRequestDto> updateProductOptionRequestDtoList =
                updateProductOptionRequestVo.stream()
                        .map(UpdateProductOptionRequestDto::from)
                        .toList();

        productOptionService.updateProductOptionList(updateProductOptionRequestDtoList);
        return new BaseResponse<>();
    }

    @Operation(summary = "상품에 등록된 옵션 정보 리스트 조회", description = """
    사이즈, 용량, 재고를 조회합니다.
    """
    , tags = {"상품 정보 조회"})
    @GetMapping("/details/{productCode}")
    public BaseResponse<List<GetProductOptionListResponseVo>> getProductOptionList(@PathVariable String productCode) {

        return new BaseResponse<>(
                productOptionService.getProductOptionList(productCode).stream()
                        .map(GetProductOptionListResponseDto::toVo)
                        .toList()
        );
    }
}
