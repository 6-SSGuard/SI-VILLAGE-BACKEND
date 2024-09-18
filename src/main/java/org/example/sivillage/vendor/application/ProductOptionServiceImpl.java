package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.vendor.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductOptionListResponseDto;
import org.example.sivillage.vendor.infrastructure.ProductOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    @Override
    /**
     * 2. 상품 옵션 등록
     * @param createProductOptionRequestDto 상품 옵션 등록 요청 DTO
     */
    public void addProductOption(CreateProductOptionRequestDto createProductOptionRequestDto) {

        productOptionRepository.save(createProductOptionRequestDto.toEntity());
    }

    /**
     * 5. 상품 옵션 정보 조회
     * @param productCode 상품 코드
     * @return GetProductOptionListResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public List<GetProductOptionListResponseDto> getProductOptionList(String productCode) {
        return productOptionRepository.findByProductCode(productCode)
                .stream()
                .map(GetProductOptionListResponseDto::from)
                .toList();
    }


}
