package org.example.sivillage.product.vo;

import lombok.Getter;

@Getter
public class GetProductDetailsResponseVo {
    private String productCode;
    private String productName;
    private Integer price;
    private String brandName;
    private String color;
    private String size;
    private String capacity;
    private Integer stock;
    private Long likesCount;
}
