package org.example.sivillage.vendor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductCategoryList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topCategoryCode;

    @Column(nullable = false)
    private String middleCategoryCode;

    @Column(nullable = false)
    private String bottomCategoryCode;

    @Column(nullable = true)
    private String subCategoryCode;

    @Column(nullable = false)
    private String productCode;
}
