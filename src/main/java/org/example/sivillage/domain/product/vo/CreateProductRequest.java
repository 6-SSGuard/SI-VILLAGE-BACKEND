package org.example.sivillage.domain.product.vo;

import lombok.Getter;
import org.example.sivillage.domain.product.domain.Color;
import org.example.sivillage.domain.product.domain.Size;

@Getter
public class CreateProductRequest {
    private String name;
    private Integer price;
    private Integer stock;
    private Color color;
    private String capacity;
    private Size size;
    private String brand;
}
