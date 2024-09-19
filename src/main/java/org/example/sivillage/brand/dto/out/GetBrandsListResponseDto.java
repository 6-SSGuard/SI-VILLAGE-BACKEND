package org.example.sivillage.brand.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.domain.BrandLike;
import org.example.sivillage.brand.vo.out.GetBrandsListResponseVo;

@NoArgsConstructor
@Getter
public class GetBrandsListResponseDto {
    private Long brandId;
    private String brandEngName;
    private String brandKorName;
    private boolean isLiked;
    private String brandIndexLetter;
    private String brandIndexLetterKor;

    @Builder
    public GetBrandsListResponseDto(Long brandId, String brandEngName, String brandKorName, boolean isLiked, String brandIndexLetter, String brandIndexLetterKor) {
        this.brandId = brandId;
        this.brandEngName = brandEngName;
        this.brandKorName = brandKorName;
        this.isLiked = isLiked;
        this.brandIndexLetter = brandIndexLetter;
        this.brandIndexLetterKor = brandIndexLetterKor;
    }

    public static GetBrandsListResponseDto of(Brand brand, BrandLike brandLike) {
        return GetBrandsListResponseDto.builder()
                .brandId(brand.getId())
                .brandEngName(brand.getBrandEngName())
                .brandKorName(brand.getBrandKorName())
                .isLiked(brandLike.isLiked())
                .brandIndexLetter(brand.getBrandIndexLetter())
                .brandIndexLetterKor(brand.getBrandIndexLetterKor())
                .build();
    }

    public static GetBrandsListResponseVo toVo(GetBrandsListResponseDto getBrandsListResponseDto) {
        return GetBrandsListResponseVo.builder()
                .brandId(getBrandsListResponseDto.getBrandId())
                .brandEngName(getBrandsListResponseDto.getBrandEngName())
                .brandKorName(getBrandsListResponseDto.getBrandKorName())
                .isLiked(getBrandsListResponseDto.isLiked())
                .brandIndexLetter(getBrandsListResponseDto.getBrandIndexLetter())
                .brandIndexLetterKor(getBrandsListResponseDto.getBrandIndexLetterKor())
                .build();
    }
}
