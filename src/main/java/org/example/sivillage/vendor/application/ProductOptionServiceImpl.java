package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.vendor.domain.ProductOption;
import org.example.sivillage.vendor.dto.in.CreateProductOptionRequestDto;
import org.example.sivillage.vendor.dto.in.UpdateProductOptionRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductOptionListResponseDto;
import org.example.sivillage.vendor.infrastructure.ProductOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    @Override
    /**
     * 2. 상품 옵션 등록
     * @param createProductOptionRequestDto 상품 옵션 등록 요청 DTO
     */
    public void addProductOptionList(List<CreateProductOptionRequestDto> createProductOptionRequestDto) {
        // dangerStock 값 확인
        for (CreateProductOptionRequestDto dto : createProductOptionRequestDto) {
            if (dto.getDangerStock() < 0) {
                log.error("dangerStock 값이 0보다 작습니다. dto: {}", dto);
                throw new IllegalArgumentException("dangerStock 값이 0보다 작습니다.");
            }
        }


        for (CreateProductOptionRequestDto dto : createProductOptionRequestDto) {
            if (dto.getDangerStock() < 0) {
                log.error("dangerStock 값이 0보다 작습니다. dto: {}", dto);
                throw new IllegalArgumentException("dangerStock 값이 0보다 작습니다.");
            }
        }
        List<ProductOption> productOption = createProductOptionRequestDto.stream()
                .map(CreateProductOptionRequestDto::toEntity)
                .toList();

        productOptionRepository.saveAll(productOption);
    }

    @Override
    public void updateProductOptionList(List<UpdateProductOptionRequestDto> updateProductOptionRequestDto) {
        for (UpdateProductOptionRequestDto dto : updateProductOptionRequestDto) {
            if (dto.getDangerStock() < 0) {
                log.error("dangerStock 값이 0보다 작습니다. dto: {}", dto);
                throw new IllegalArgumentException("dangerStock 값이 0보다 작습니다.");
            }
        }

        List<ProductOption> productOption = updateProductOptionRequestDto.stream()
                .map(dto -> {
                    return dto.updateEntity(dto.getId());
                })
                .toList();

        productOptionRepository.saveAll(productOption);
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
