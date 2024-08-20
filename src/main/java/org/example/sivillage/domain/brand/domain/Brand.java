package org.example.sivillage.domain.brand.domain;

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
    private String name;

    @Builder
    public Brand(String name) {
        this.name = name;
    }

    @Builder
    public static Brand createBrand(String name) {
        return Brand.builder()
                .name(name)
                .build();
    }
}
