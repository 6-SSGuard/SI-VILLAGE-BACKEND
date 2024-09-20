package org.example.sivillage.vendor.application;

import org.example.sivillage.vendor.dto.in.AddProductByVendorRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductByVendorResponseDto;

import java.util.List;

public interface ProductByVendorService {


    void addProductByVendor(AddProductByVendorRequestDto addProductByVendorRequestDto);

    void changeProductByVendor(AddProductByVendorRequestDto addProductByVendorRequestDto);

    void deleteProductByVendor(Long productByVendorId);

    List<GetProductByVendorResponseDto> getProductByVendorList(String vendorName);

    GetProductByVendorResponseDto getProductByVendor(String productCode);
}
