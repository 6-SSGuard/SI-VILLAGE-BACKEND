package org.example.sivillage.product.vo.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDetailsResponseVo {
    private String productUuid;
    private String productName;
    private Integer price;
    private String brandEngName;
    private String brandKorName;
    private String color;
    private String size;
    private String capacity;
    private Integer stock;
    private Integer likesCount;
    private boolean isLiked;
}
