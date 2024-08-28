package org.example.sivillage.brand.presentation;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandsListResponseDto;
import org.example.sivillage.brand.vo.in.AddBrandRequestVo;
import org.example.sivillage.brand.vo.out.GetBrandsResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper mapper;

    @Operation(summary = "브랜드 추가")
    @PostMapping("/")
    public BaseResponse<Void> addBrand(@Valid @RequestBody AddBrandRequestVo addBrandRequestVo) {
        AddBrandRequestDto request = mapper.map(addBrandRequestVo, AddBrandRequestDto.class);
        brandService.addBrand(request);

        return new BaseResponse<>();
    }

    @Operation(summary = "브랜드 목록 조회")
    @GetMapping("/")
    public BaseResponse<GetBrandsResponseVo> getBrands() {
        GetBrandsListResponseDto getBrandsList = brandService.getBrands();

        // Mapper를 사용하여 GetBrandslistResponseDto를 GetBrandsResponseVo로 매핑
        GetBrandsResponseVo response = mapper.map(getBrandsList, GetBrandsResponseVo.class);
        return new BaseResponse<>(response);
        //**

    }
}
