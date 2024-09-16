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
    private String productName;
    private String vendorName;
    private Boolean mainView;
    private Boolean newProduct;
    private Boolean display;
    private Integer maxOrderCount;
    private Integer minOrderCount;
    private Double discountRate;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double productLikeRate;

    @Builder
    public GetProductByVendorResponseDto(
            Long productByVendorId,
            String productCode,
            String productName,
            String vendorName,
            Boolean mainView,
            Boolean newProduct,
            Boolean display,
            Integer maxOrderCount,
            Integer minOrderCount,
            Double discountRate,
            Double purchasePrice,
            Double sellingPrice,
            Double productLikeRate
    ) {
        this.productByVendorId = productByVendorId;
        this.productCode = productCode;
        this.productName = productName;
        this.vendorName = vendorName;
        this.mainView = mainView;
        this.newProduct = newProduct;
        this.display = display;
        this.maxOrderCount = maxOrderCount;
        this.minOrderCount = minOrderCount;
        this.discountRate = discountRate;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.productLikeRate = productLikeRate;
    }

    public static GetProductByVendorResponseDto from(ProductByVendor productByVendor) {
        return GetProductByVendorResponseDto.builder()
                .productByVendorId(productByVendor.getId())
                .productCode(productByVendor.getProductCode())
                .productName(productByVendor.getProductName())
                .vendorName(productByVendor.getVendorName())
                .mainView(productByVendor.getMainView())
                .newProduct(productByVendor.getNewProduct())
                .display(productByVendor.getDisplay())
                .maxOrderCount(productByVendor.getMaxOrderCount())
                .minOrderCount(productByVendor.getMinOrderCount())
                .discountRate(productByVendor.getDiscountRate())
                .purchasePrice(productByVendor.getPurchasePrice())
                .sellingPrice(productByVendor.getSellingPrice())
                .productLikeRate(productByVendor.getProductLikeRate())
                .build();
    }

    public GetProductByVendorResponseVo toVo() {
        return GetProductByVendorResponseVo.builder()
                .productByVendorId(this.productByVendorId)
                .productCode(this.productCode)
                .productName(this.productName)
                .vendorName(this.vendorName)
                .mainView(this.mainView)
                .newProduct(this.newProduct)
                .display(this.display)
                .maxOrderCount(this.maxOrderCount)
                .minOrderCount(this.minOrderCount)
                .discountRate(this.discountRate)
                .purchasePrice(this.purchasePrice)
                .sellingPrice(this.sellingPrice)
                .productLikeRate(this.productLikeRate)
                .build();
    }
}
