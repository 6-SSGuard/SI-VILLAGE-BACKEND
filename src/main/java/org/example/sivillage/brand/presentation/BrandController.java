package org.example.sivillage.brand.presentation;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandsResponseDto;
import org.example.sivillage.brand.vo.in.AddBrandRequestVo;
import org.example.sivillage.brand.vo.out.GetBrandsResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "브랜드 목록 조회", description = """
        매개변수 없으면 전체 조회,
        a~z, ㄱ~ㅎ: 해당 문자를 기준으로 필터링 후 오름차순 정렬
        """)
    @GetMapping("/")
    BaseResponse<GetBrandsResponseVo> getBrands() {
        List<GetBrandsResponseDto> getBrandsResponseDto = brandService.getBrands();
        GetBrandsResponseVo response = new GetBrandsResponseVo();
        response.setBrands(getBrandsResponseDto);

        return new BaseResponse<>(response);
    }
}
