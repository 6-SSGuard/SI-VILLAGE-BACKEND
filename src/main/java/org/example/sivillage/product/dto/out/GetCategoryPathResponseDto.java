package org.example.sivillage.product.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryPathResponseDto {
    private List<String> categoryPath;
}
