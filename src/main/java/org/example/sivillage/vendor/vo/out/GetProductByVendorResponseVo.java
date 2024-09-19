package org.example.sivillage.vendor.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductByVendorResponseVo {

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
    public GetProductByVendorResponseVo(
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
}
