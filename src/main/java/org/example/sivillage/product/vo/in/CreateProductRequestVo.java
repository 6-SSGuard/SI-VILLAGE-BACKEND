package org.example.sivillage.product.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.example.sivillage.product.domain.Color;
import org.example.sivillage.productoption.domain.Size;

import java.util.List;

@Getter
public class CreateProductRequestVo {
    @Schema(description = "물품 이름", example = "샘플 물품")
    @NotBlank(message = "물품 이름은 필수 값입니다.")
    private String productName;

    @Schema(description = "물품 가격", example = "1000")
    @NotNull(message = "물품 가격은 필수 값입니다.")
    private Integer price;

    @Schema(description = "물품 재고 수량", example = "100")
    @NotNull(message = "물품 재고 수량은 필수 값입니다.")
    private Integer stock;

    @Schema(description = "물품 색상", example = "RED")
    @NotNull(message = "물품 색상은 필수 값입니다.")
    private Color color;

    @Schema(description = "물품 용량", example = "500ml")
    @NotBlank(message = "물품 용량은 필수 값입니다.")
    private String capacity;

    @Schema(description = "물품 크기", example = "M")
    @NotNull(message = "물품 크기는 필수 값입니다.")
    private Size size;

    @Schema(description = "브랜드 영어 이름", example = "brand name")
    @NotBlank(message = "브랜드 영어 이름은 필수 값입니다.")
    private String brandEngName;

    @Schema(description = "물품 상세 설명 html", example = "html 형태의 string")
    @NotBlank(message = "상세 설명은 필수 값입니다.")
    private String detailContent;

    @Schema(description = "물품 이미지 URL 목록", example = "[\"url1\", \"url2\"]")
    @NotNull(message = "물품 이미지 URL은 필수 값입니다.")
    private List<String> productImageUrls;

    @Schema(description = "카테고리 코드")
    @NotNull(message = "카테고리 코드는 필수 값입니다.")
    private String categoryCode;
}
