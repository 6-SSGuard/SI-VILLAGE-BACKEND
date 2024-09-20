package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.vendor.application.ProductByVendorService;
import org.example.sivillage.vendor.dto.in.AddProductByVendorRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductByVendorResponseDto;
import org.example.sivillage.vendor.vo.in.AddProductByVendorRequestVo;
import org.example.sivillage.vendor.vo.out.GetProductByVendorResponseVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendor/product")
@Tag(name = "벤더의 상품 관리 API")
public class ProductByVendorController {

    private final ProductByVendorService productByVendorService;

    @Operation(summary = "벤더의 상품 등록", description = "상품을 등록합니다.")
    @PostMapping
    public BaseResponse<Void> addProductByVendor(
            @RequestBody AddProductByVendorRequestVo addProductByVendorRequestVo
            ) {

        productByVendorService.addProductByVendor(AddProductByVendorRequestDto.from(addProductByVendorRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "벤더의 상품 수정", description = "상품을 수정합니다.")
    @PutMapping
    public BaseResponse<Void> changeProductByVendor(
            @RequestBody AddProductByVendorRequestVo addProductByVendorRequestVo) {

        productByVendorService.changeProductByVendor(AddProductByVendorRequestDto.from(addProductByVendorRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "벤더 상품 삭제", description = "상품을 삭제합니다.")
    @DeleteMapping("/{productByVendorId}")
    public BaseResponse<Void> deleteProductByVendor(
            @PathVariable Long productByVendorId) {

        productByVendorService.deleteProductByVendor(productByVendorId);
        return new BaseResponse<>();
    }

    @Operation(summary = "벤더의 상품 리스트 조회", description = "상품을 조회합니다.")
    @GetMapping("/by-vendor/{vendorName}")
    public BaseResponse<List<GetProductByVendorResponseVo>> getProductByVendorList(
            @PathVariable String vendorName) {

        return new BaseResponse<>(
                productByVendorService.getProductByVendorList(vendorName).stream()
                        .map(GetProductByVendorResponseDto::toVo)
                        .toList()
        );
    }

    @Operation(summary = "벤더의 상품 조회", description = """
    벤더의 이름, 메인화면 노출 여부, 신상품 여부, 상품 노출 여부, 최대 주문 수량, 최소 주문 수량, 할인율을 조회합니다.
    """)
    @GetMapping("/{productCode}")
    public BaseResponse<GetProductByVendorResponseVo> getProductByVendor(
            @PathVariable String productCode) {

        return new BaseResponse<>(
                productByVendorService.getProductByVendor(productCode).toVo()
        );
    }
}
