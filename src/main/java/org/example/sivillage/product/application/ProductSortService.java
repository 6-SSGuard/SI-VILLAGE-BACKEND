package org.example.sivillage.product.application;

import org.example.sivillage.global.common.response.dto.IdListResponseDto;

import java.util.List;

public interface ProductSortService {
    List<IdListResponseDto<String>> getSortBestProduct(String sort);
}
