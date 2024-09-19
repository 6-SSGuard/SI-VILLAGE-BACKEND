package org.example.sivillage.vendor.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.vendor.domain.ProductByVendor;
import org.example.sivillage.vendor.vo.in.AddProductByVendorRequestVo;

@Getter
@NoArgsConstructor
public class AddProductByVendorRequestDto {

    private String productCode;
    private String vendorName;
    private Boolean mainView;
    private Boolean newProduct;
    private Boolean display;
    private Integer maxOrderCount;
    private Integer minOrderCount;
    private Double discountRate;

    @Builder
    public AddProductByVendorRequestDto(
            String productCode,
            String vendorName,
            Boolean mainView,
            Boolean newProduct,
            Boolean display,
            Integer maxOrderCount,
            Integer minOrderCount,
            Double discountRate
    ) {
        this.productCode = productCode;
        this.vendorName = vendorName;
        this.mainView = mainView;
        this.newProduct = newProduct;
        this.display = display;
        this.maxOrderCount = maxOrderCount;
        this.minOrderCount = minOrderCount;
        this.discountRate = discountRate;
    }

    public ProductByVendor toEntity() {
        return ProductByVendor.builder()
                .productCode(productCode)
                .vendorName(vendorName)
                .mainView(mainView)
                .newProduct(newProduct)
                .display(display)
                .maxOrderCount(maxOrderCount)
                .minOrderCount(minOrderCount)
                .discountRate(discountRate)
                .build();
    }

    public static AddProductByVendorRequestDto from(AddProductByVendorRequestVo addProductByVendorVo) {
        return AddProductByVendorRequestDto.builder()
                .productCode(addProductByVendorVo.getProductCode())
                .vendorName(addProductByVendorVo.getVendorName())
                .mainView(addProductByVendorVo.getMainView())
                .newProduct(addProductByVendorVo.getNewProduct())
                .display(addProductByVendorVo.getDisplay())
                .maxOrderCount(addProductByVendorVo.getMaxOrderCount())
                .minOrderCount(addProductByVendorVo.getMinOrderCount())
                .discountRate(addProductByVendorVo.getDiscountRate())
                .build();
    }
}
