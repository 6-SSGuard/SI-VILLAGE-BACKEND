package org.example.sivillage.vendor.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class ProductByVendor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String vendorName;

    @Column(nullable = false)
    private Boolean mainView;

    @Column(nullable = false)
    private Boolean newProduct;

    @Column(nullable = false)
    private Boolean display;

    @Column(nullable = false)
    private Integer maxOrderCount;

    @Column(nullable = false)
    private Integer minOrderCount;

    @Column(nullable = false)
    private Double discountRate;

    @Builder
    public ProductByVendor(
            Long id,
            String productCode,
            String vendorName,
            Boolean mainView,
            Boolean newProduct,
            Boolean display,
            Integer maxOrderCount,
            Integer minOrderCount,
            Double discountRate
    ) {
        this.id = id;
        this.productCode = productCode;
        this.vendorName = vendorName;
        this.mainView = mainView;
        this.newProduct = newProduct;
        this.display = display;
        this.maxOrderCount = maxOrderCount;
        this.minOrderCount = minOrderCount;
        this.discountRate = discountRate;
    }

    @Builder
    public ProductByVendor(
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


}
