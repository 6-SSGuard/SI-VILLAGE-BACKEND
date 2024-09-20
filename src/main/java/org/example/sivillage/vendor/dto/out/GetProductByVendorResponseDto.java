package org.example.sivillage.vendor.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.vendor.domain.ProductByVendor;
import org.example.sivillage.vendor.vo.out.GetProductByVendorResponseVo;

@Getter
@NoArgsConstructor
public class GetProductByVendorResponseDto {

    private Long productByVendorId;
    private String productCode;
    private String vendorName;
    private Boolean mainView;
    private Boolean newProduct;
    private Boolean display;
    private Integer maxOrderCount;
    private Integer minOrderCount;
    private Double discountRate;

    @Builder
    public GetProductByVendorResponseDto(
            Long productByVendorId,
            String productCode,
            String vendorName,
            Boolean mainView,
            Boolean newProduct,
            Boolean display,
            Integer maxOrderCount,
            Integer minOrderCount,
            Double discountRate
    ) {
        this.productByVendorId = productByVendorId;
        this.productCode = productCode;
        this.vendorName = vendorName;
        this.mainView = mainView;
        this.newProduct = newProduct;
        this.display = display;
        this.maxOrderCount = maxOrderCount;
        this.minOrderCount = minOrderCount;
        this.discountRate = discountRate;
    }

    public static GetProductByVendorResponseDto from(ProductByVendor productByVendor) {
        return GetProductByVendorResponseDto.builder()
                .productByVendorId(productByVendor.getId())
                .productCode(productByVendor.getProductCode())
                .vendorName(productByVendor.getVendorName())
                .mainView(productByVendor.getMainView())
                .newProduct(productByVendor.getNewProduct())
                .display(productByVendor.getDisplay())
                .maxOrderCount(productByVendor.getMaxOrderCount())
                .minOrderCount(productByVendor.getMinOrderCount())
                .discountRate(productByVendor.getDiscountRate())
                .build();
    }

    public GetProductByVendorResponseVo toVo() {
        return GetProductByVendorResponseVo.builder()
                .productByVendorId(this.productByVendorId)
                .productCode(this.productCode)
                .vendorName(this.vendorName)
                .mainView(this.mainView)
                .newProduct(this.newProduct)
                .display(this.display)
                .maxOrderCount(this.maxOrderCount)
                .minOrderCount(this.minOrderCount)
                .discountRate(this.discountRate)
                .build();
    }
}
