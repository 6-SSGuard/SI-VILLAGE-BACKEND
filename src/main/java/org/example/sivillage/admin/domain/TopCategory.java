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
public class TopCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "top_category_id")
    private Integer topCategoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryCode;
}
