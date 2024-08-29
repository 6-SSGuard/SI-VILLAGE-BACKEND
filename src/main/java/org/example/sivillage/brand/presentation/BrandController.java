package org.example.sivillage.brand.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.vo.in.AddBrandRequestVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "브랜드 관리 API", description = "브랜드 관련 API endpoints")
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
}
