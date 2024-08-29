package org.example.sivillage.member.vo.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sivillage.product.dto.out.GetProductsUuidResponseDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetProductsUuidResponseVo {
    private List<GetProductsUuidResponseDto> productsUuidList;
}