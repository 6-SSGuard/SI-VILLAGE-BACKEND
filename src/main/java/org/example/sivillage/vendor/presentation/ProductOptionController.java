package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.vendor.dto.out.GetProductOptionListResponseDto;
import org.example.sivillage.vendor.vo.out.GetProductOptionListResponseVo;
import org.example.sivillage.vendor.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.vendor.vo.in.CreateProductOptionRequestVo;
import org.example.sivillage.vendor.application.ProductOptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @Operation(summary = "상품 옵션 등록")
    @PostMapping("/option")
    public BaseResponse<Void> addProductOption(@Valid @RequestBody CreateProductOptionRequestVo createProductOptionRequestVo) {

        productOptionService.addProductOption(CreateProductOptionRequestDto.from(createProductOptionRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "상품에 등록된 옵션 정보 리스트 조회")
    @GetMapping("/details/option/{productCode}")
    public BaseResponse<List<GetProductOptionListResponseVo>> getProductOptionList(@PathVariable String productCode) {

        return new BaseResponse<>(
                productOptionService.getProductOptionList(productCode).stream()
                        .map(GetProductOptionListResponseDto::toVo)
                        .toList()
        );
    }
}
