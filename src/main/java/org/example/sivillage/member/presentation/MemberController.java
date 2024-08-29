package org.example.sivillage.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.CustomUserDetails;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.dto.out.GetBrandsListResponseDto;
import org.example.sivillage.brand.vo.out.GetBrandsResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.member.application.BeautyInfoService;
import org.example.sivillage.member.application.SizeInfoService;
import org.example.sivillage.member.application.BrandLikeService;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.member.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.member.dto.out.SizeInfoResponseDto;
import org.example.sivillage.member.vo.in.BeautyInfoRequestVo;
import org.example.sivillage.member.vo.out.BeautyInfoResponseVo;
import org.example.sivillage.member.vo.in.SizeInfoRequestVo;
import org.example.sivillage.member.vo.out.SizeInfoResponseVo;
import org.example.sivillage.member.vo.out.GetProductsUuidResponseVo;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.product.dto.out.GetProductBriefInfoResponseDto;
import org.example.sivillage.product.dto.out.GetProductDetailsResponseDto;
import org.example.sivillage.product.dto.out.GetProductsUuidListResponseDto;
import org.example.sivillage.product.vo.out.GetProductBriefInfoResponseVo;
import org.example.sivillage.product.vo.out.GetProductDetailsResponseVo;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 관리 API", description = "회원 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final BeautyInfoService beautyInfoService;
    private final SizeInfoService sizeInfoService;
    private final BrandLikeService brandLikeService;
    private final BrandService brandService;
    private final ProductLikeService productLikeService;
    private final ProductService productService;
    private final ModelMapper mapper;

    @Operation(summary = "뷰티 정보 등록", description = "뷰티정보를 등록합니다.")
    @PostMapping("/beauty-info")
    public BaseResponse<Void> addBeautyInfo(@Valid @RequestBody BeautyInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        beautyInfoService.addBeautyInfo(BeautyInfoRequestVo.toDto(vo),memberUuid); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary = "뷰티 정보 조회", description = "뷰티정보를 조회합니다.")
    @GetMapping("/beauty-info")
    public BaseResponse<BeautyInfoResponseVo> getBeautyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        BeautyInfoResponseDto dto = beautyInfoService.getBeautyInfo(memberUuid);
        BeautyInfoResponseVo vo = mapper.map(dto, BeautyInfoResponseVo.class);
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "뷰티 정보 수정", description = "뷰티정보를 수정합니다.")
    @PutMapping("/beauty-info")
    public BaseResponse<Void> changeBeautyInfo(@Valid @RequestBody BeautyInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        beautyInfoService.changeBeautyInfo(BeautyInfoRequestVo.toDto(vo),memberUuid);
        return new BaseResponse<>();
    }

    @Operation(summary = "뷰티 정보 삭제", description = "뷰티 정보를 삭제합니다.")
    @DeleteMapping("/beauty-info")
    public BaseResponse<Void> deleteBeautyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        beautyInfoService.removeBeautyInfo(memberUuid);
        return new BaseResponse<>();
    }

    @Operation(summary = "사이즈 정보 등록", description = "사이즈 정보를 등록합니다.")
    @PostMapping("/size-info")
    public BaseResponse<Void> addSizeInfo(@Valid @RequestBody SizeInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        sizeInfoService.addSizeInfo(SizeInfoRequestVo.toDto(vo),memberUuid); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary = "사이즈 정보 조회", description = "사이즈 정보를 조회합니다.")
    @GetMapping("/size-info")
    public BaseResponse<SizeInfoResponseVo> getSizeInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        SizeInfoResponseDto dto = sizeInfoService.getSizeInfo(memberUuid);
        SizeInfoResponseVo vo = mapper.map(dto,SizeInfoResponseVo.class);
        return new BaseResponse<>(vo);
    }

    @Operation(summary = "사이즈 정보 수정", description = "사이즈 정보를 수정합니다.")
    @PutMapping("/size-info")
    public BaseResponse<Void> changeSizeInfo(@Valid @RequestBody SizeInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        sizeInfoService.changeSizeInfo(SizeInfoRequestVo.toDto(vo),memberUuid);
        return new BaseResponse<>();

    }

    @Operation(summary = "사이즈 정보 삭제", description = "사이즈 정보를 삭제합니다.")
    @DeleteMapping("/size-info")
    public BaseResponse<Void> deleteSizeInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        sizeInfoService.removeSizeInfo(memberUuid);
        return new BaseResponse<>();
    }

    @Operation(summary = "브랜드 목록 조회")
    @GetMapping("/")
    public BaseResponse<GetBrandsResponseVo> getBrands(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        GetBrandsListResponseDto getBrandsList = brandService.getBrands(customUserDetails.getMemberUuid());
        // Mapper를 사용하여 GetBrandslistResponseDto를 GetBrandsResponseVo로 매핑
        GetBrandsResponseVo response = mapper.map(getBrandsList, GetBrandsResponseVo.class);
        return new BaseResponse<>(response);
        //**

    }

    @Operation(summary = "브랜드 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/brand/like/{brandId}")
    public BaseResponse<Void> toggleBrandLike(@PathVariable Long brandId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        brandLikeService.toggleBrandLike(brandId, customUserDetails.getMemberUuid());
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

    @Operation(summary = "상품 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/product/like/{productUuid}")
    public BaseResponse<Void> toggleProductLike(@PathVariable String productUuid, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        productLikeService.toggleProductLike(productUuid, customUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 리스트에 대한 Uuid 조회", description = """
            상품 리스트에 대한 Uuid들을 받아오고, 단일 상품 간략 정보 조회하는 api로 해당 Uuid값을 전달해서 요청하기
            이미 프론트에서 받아온 정보는 캐싱처리
            """)
    @GetMapping("/products/uuid")
    public BaseResponse<GetProductsUuidResponseVo> getProductsUuid() {
        GetProductsUuidListResponseDto responseDto = productService.getProductsUuid();
        GetProductsUuidResponseVo response = mapper.map(responseDto, GetProductsUuidResponseVo.class);
        return new BaseResponse<>(response);
    }

    @Operation(summary = "단일 상품에 대한 간략 정보 조회", description = """
        '상품 리스트에 대한 Uuid 조회' api로 얻어낸 Uuid 정보를 전달하고, 이 api로 받아온 값을 상품 리스트 렌더링에 활용.
        이미 프론트에서 받아온 정보는 캐싱처리
        """)
    @GetMapping("/product/brief/{productUuid}")
    public BaseResponse<GetProductBriefInfoResponseVo> getProductBriefInfo(@PathVariable String productUuid, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        GetProductBriefInfoResponseDto responseDto = productService.getProductBriefInfo(productUuid, memberUuid);
        GetProductBriefInfoResponseVo response = mapper.map(responseDto, GetProductBriefInfoResponseVo.class);
        return new BaseResponse<>(response);
    }
}















