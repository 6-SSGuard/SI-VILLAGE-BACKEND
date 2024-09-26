package org.example.sivillage.global.common.response.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductDto {
    private String productCode;
    private Object value;  // createdDate 또는 price가 될 수 있음

    public ProductDto(String productCode, Object value) {
        this.productCode = productCode;
        this.value = value;
    }

    // Getter 및 Setter
    public String getProductCode() {
        return productCode;
    }

    public Object getValue() {
        return value;
    }
}