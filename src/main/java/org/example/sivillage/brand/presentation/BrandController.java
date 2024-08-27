package org.example.sivillage.brand.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.global.common.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    @Operation(summary = "브랜드 추가")
    @PostMapping("/")
    public BaseResponse<?> addBrand(@RequestBody AddBrandRequestDto request) {
        brandService.addBrand(request);

        return new BaseResponse<>();
    }

    @Operation(summary = "브랜드 목록 조회", description = """
        매개변수 없으면 전체 조회,
        a~z, ㄱ~ㅎ: 해당 문자를 기준으로 필터링 후 오름차순 정렬
        """)
    @GetMapping("/")
    BaseResponse<?> getBrands() {
        return new BaseResponse<>(
                brandService.getBrands()
        );
    }
}
