package org.example.sivillage.domain.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.domain.brand.application.BrandService;
import org.example.sivillage.domain.member.application.MemberService;
import org.example.sivillage.domain.brand.domain.Brand;
import org.example.sivillage.domain.product.domain.Product;
import org.example.sivillage.domain.product.domain.ProductInfo;
import org.example.sivillage.domain.brand.infrastructure.BrandRepository;
import org.example.sivillage.domain.product.infrastructure.ProductInfoRepository;
import org.example.sivillage.domain.product.infrastructure.ProductRepository;
import org.example.sivillage.domain.product.vo.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final MemberService memberService;
    private final BrandRepository brandRepository;
    private final ProductInfoRepository productInfoRepository;
    private final BrandService brandService;

    public void createProduct(CreateProductRequest request) {
        /** Brand 정보가 DB에 있는지 확인
            Brand 정보가 없으면 새로 생성
            Brand 정보가 있으면 그 객체를 받아오기
         **/
        Brand brand = brandService.createBrand(request.getBrand());

        Product product = Product.createProduct(request, brand);
        ProductInfo productInfo = ProductInfo.createProductInfo(request, product);
        productInfoRepository.save(productInfo);
        productRepository.save(product);
    }
}
