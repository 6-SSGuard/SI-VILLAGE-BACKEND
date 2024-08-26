package org.example.sivillage.brand.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String brandName;

    @Builder
    public Brand(String brandName) {
        this.brandName = brandName;
    }

    @Builder
    public static Brand createBrand(String brandName) {
        return Brand.builder()
                .brandName(brandName)
                .build();
    } // 브랜드 crud는 따로 뺴기
}
