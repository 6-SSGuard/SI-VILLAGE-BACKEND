package org.example.sivillage.brand.vo.out;

import lombok.Getter;
import lombok.Setter;
import org.example.sivillage.brand.dto.out.GetBrandsResponseDto;

import java.util.List;

@Getter
@Setter
public class GetBrandsResponseVo {
    private List<GetBrandsResponseDto> brands;
}
