package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.vendor.domain.ProductImage;
import org.example.sivillage.vendor.dto.in.CreateProductImageListRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductImageUrlListResponseDto;
import org.example.sivillage.vendor.dto.out.GetProductThumbnailUrlResponseDto;
import org.example.sivillage.vendor.infrastructure.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;


    /**
     * 3. 상품 이미지 등록
     * @param createProductImageListRequestDto 상품 이미지 등록 요청 DTO
     */
    @Override
    public void addProductImageList(List<CreateProductImageListRequestDto> createProductImageListRequestDto) {

        List<ProductImage> productImageList = createProductImageListRequestDto.stream()
                .map(CreateProductImageListRequestDto::toEntity)
                .toList();

        productImageRepository.saveAll(productImageList);
    }

    @Override
    public void updateProductImageList(List<CreateProductImageListRequestDto> createProductImageListRequestDto) {

        List<ProductImage> productImageList = createProductImageListRequestDto.stream()
                .map(dto -> {
                    Long id = productImageRepository.findByProductCode(dto.getProductCode()).getId();
                    return dto.updateEntity(id);
                })
                .toList();

        productImageRepository.saveAll(productImageList);
    }

    /**
     * 7. 상품 썸네일 URL 조회
     * @param productCode 상품 코드
     * @return GetProductThumbnailUrlResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public GetProductThumbnailUrlResponseDto getProductThumbnailUrl(String productCode) {
        String thumbnailUrl = productImageRepository.findByProductCodeAndThumbnailTrue(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_IMAGE_NOT_FOUND)).getProductImageUrl();

        return GetProductThumbnailUrlResponseDto.from(thumbnailUrl);
    }

    /**
     * 8. 상품 이미지 리스트 URL 조회
     * @param productCode 상품 코드
     * @return GetProductImageUrlListResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public List<GetProductImageUrlListResponseDto> getProductImageUrlList(String productCode) {
        return productImageRepository.findProductImageUrlsByProductCode(productCode)
                .stream()
                .map(GetProductImageUrlListResponseDto::from)
                .toList();
    }
}
