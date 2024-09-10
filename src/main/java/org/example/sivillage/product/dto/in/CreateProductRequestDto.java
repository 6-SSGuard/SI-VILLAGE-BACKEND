package org.example.sivillage.product.dto.in;

import lombok.*;
import org.example.sivillage.product.domain.Color;
import org.example.sivillage.productoption.Size;
import org.example.sivillage.sizeinfo.domain.sizeenum.ShoeSize;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductRequestDto {
    private String productName;

    private Integer price;

    private Integer stock;

    private Color color;

    private String volume;

    private Size size;

    private ShoeSize shoeSize;

    private String brandEngName;

    private String detailContent;

    private List<ProductImageDto> productImageUrls;

    private String categoryCode;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class ProductImageDto {
        private String productImageUrl;
        private boolean thumbnail;
    }
}