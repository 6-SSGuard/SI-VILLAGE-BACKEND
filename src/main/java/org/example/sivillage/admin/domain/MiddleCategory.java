package org.example.sivillage.admin.domain;

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
public class MiddleCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "middle_category_id")
    private Integer middleCategoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_category_id", nullable = false)
    private TopCategory topCategory;

}
