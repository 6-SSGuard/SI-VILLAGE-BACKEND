package org.example.sivillage.brand.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBrandsListResponseDto {
    private List<GetBrandsResponseDto> brands;
}
