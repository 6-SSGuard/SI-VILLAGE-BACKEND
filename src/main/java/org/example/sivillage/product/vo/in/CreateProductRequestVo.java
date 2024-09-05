package org.example.sivillage.product.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.sivillage.product.domain.Color;

import java.util.List;

@Getter
public class CreateProductRequestVo {
    @Schema(description = "상품 이름", example = "샘플 상품")
    @NotBlank(message = "상품 이름은 필수 값입니다.")
    private String productName;

    @Schema(description = "상품 가격", example = "1000")
    @NotNull(message = "상품 가격은 필수 값입니다.")
    private Integer price;

    @Schema(description = "상품 재고 수량", example = "100")
    @NotNull(message = "상품 재고 수량은 필수 값입니다.")
    private Integer stock;

    @Schema(description = "상품 색상", example = "RED")
    private Color color;

    @Schema(description = "상품 용량", example = "500ml")
    private String volume;

    @Schema(description = "상품 사이즈(옷)", example = "M")
    private String size;

    @Schema(description = "브랜드 영어 이름", example = "brand name")
    @NotBlank(message = "브랜드 영어 이름은 필수 값입니다.")
    private String brandEngName;

    @Schema(description = "상품 상세 설명 html", example = "html 형태의 string")
    private String detailContent;

    @Schema(description = "상품 이미지 URL 목록")
    @NotNull(message = "상품 이미지 URL은 필수 값입니다.")
    private List<ProductImageDto> productImageUrls;

    @Schema(description = "카테고리 코드")
    @NotNull(message = "카테고리 코드는 필수 값입니다.")
    private String categoryCode;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class ProductImageDto {
        private String productImageUrl;
        private boolean isThumbnail;
    }

}
