package org.example.sivillage.vendor.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Getter
@NoArgsConstructor
@Entity
public class ProductByVendor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String vendorName;

    private Boolean mainView = false;
    private Boolean newProduct = true;
    private Boolean display = false;
    private Integer maxOrderCount;

    @Column(nullable = false)
    private Integer minOrderCount = 1;

    private Double discountRate;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double productLikeRate = 0.0;

    @Builder
    public ProductByVendor(
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
