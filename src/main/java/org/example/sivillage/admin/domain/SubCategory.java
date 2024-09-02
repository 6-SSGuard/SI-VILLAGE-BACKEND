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
public class SubCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Integer subCategoryId;

    private String categoryName;
    private String categoryDescription;
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bottom_category_id")
    private BottomCategory bottomCategory;
}
