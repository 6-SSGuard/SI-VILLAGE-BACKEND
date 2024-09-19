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

    @Builder
    public AddProductByVendorRequestDto(
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
            Double sellingPrice
    ) {
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
    }

    public ProductByVendor toEntity() {
        return ProductByVendor.builder()
                .productCode(productCode)
                .productName(productName)
                .vendorName(vendorName)
                .mainView(mainView)
                .newProduct(newProduct)
                .display(display)
                .maxOrderCount(maxOrderCount)
                .minOrderCount(minOrderCount)
                .discountRate(discountRate)
                .purchasePrice(purchasePrice)
                .sellingPrice(sellingPrice)
                .build();
    }

    public static AddProductByVendorRequestDto from(AddProductByVendorRequestVo addProductByVendorVo) {
        return AddProductByVendorRequestDto.builder()
                .productCode(addProductByVendorVo.getProductCode())
                .productName(addProductByVendorVo.getProductName())
                .vendorName(addProductByVendorVo.getVendorName())
                .mainView(addProductByVendorVo.getMainView())
                .newProduct(addProductByVendorVo.getNewProduct())
                .display(addProductByVendorVo.getDisplay())
                .maxOrderCount(addProductByVendorVo.getMaxOrderCount())
                .minOrderCount(addProductByVendorVo.getMinOrderCount())
                .discountRate(addProductByVendorVo.getDiscountRate())
                .purchasePrice(addProductByVendorVo.getPurchasePrice())
                .sellingPrice(addProductByVendorVo.getSellingPrice())
                .build();
    }
}
