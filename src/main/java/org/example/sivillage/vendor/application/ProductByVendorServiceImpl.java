package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.vendor.domain.ProductByVendor;
import org.example.sivillage.vendor.dto.in.AddProductByVendorRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductByVendorResponseDto;
import org.example.sivillage.vendor.infrastructure.ProductByVendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductByVendorServiceImpl implements ProductByVendorService {

    private final ProductByVendorRepository productByVendorRepository;

    @Override
    public void addProductByVendor(AddProductByVendorRequestDto addProductByVendorRequestDto) {
        productByVendorRepository.save(addProductByVendorRequestDto.toEntity());
    }

    @Override
    public void deleteProductByVendor(Long productByVendorId) {
        productByVendorRepository.deleteById(productByVendorId);
    }

    @Override
    public List<GetProductByVendorResponseDto> getProductByVendorList(String vendorName) {
        List<ProductByVendor> productByVendorList = productByVendorRepository.findByVendorName(vendorName);
        if (!productByVendorList.isEmpty()) {
            return productByVendorList.stream()
                    .map(GetProductByVendorResponseDto::from)
                    .toList();
        }

        return List.of();
    }

    @Override
    public GetProductByVendorResponseDto getProductByVendor(String productCode) {
        return GetProductByVendorResponseDto.from(productByVendorRepository.findByProductCode(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT)));
    }
}
