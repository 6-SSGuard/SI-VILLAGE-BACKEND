package org.example.sivillage.brand.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
@Table(name = "brand", uniqueConstraints = {@UniqueConstraint(columnNames = {"brandEngName"})})
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String brandEngName;

    @Column(nullable = false, unique = true)
    private String brandKorName;
}
