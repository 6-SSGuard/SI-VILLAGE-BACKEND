package org.example.sivillage.admin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class CategoryProduct {

    @Id
    @Column(name = "category_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryProductId;

    private String categoryCode;

    private String productCode;
}
