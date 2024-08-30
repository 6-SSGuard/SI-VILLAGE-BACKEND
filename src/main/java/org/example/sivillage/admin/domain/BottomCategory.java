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
public class BottomCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bottom_category_id")
    private Integer bottomCategoryId;

    private String categoryName;
    private String categoryDescription;
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "middle_category_id")
    private MiddleCategory middleCategory;
}
