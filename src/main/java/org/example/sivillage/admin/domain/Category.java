package org.example.sivillage.admin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.admin.dto.in.AddCategoryRequestDto;
import org.example.sivillage.global.common.UuidGenerator;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false, length = 100)
    private String categoryName;

    @Column(nullable = false, length = 100, unique = true)
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent;  // 부모 카테고리 참조

    @Column(nullable = false)
    private int depth;  // 카테고리의 깊이를 나타내는 필드

    public boolean hasParent() {
        return parent != null;
    }

    public static Category createRootCategory(AddCategoryRequestDto request) {
        return Category.builder()
                .categoryName(request.getCategoryName())
                .categoryCode(UuidGenerator.generateCategoryCode())
                .depth(0)
                .parent(null)
                .build();
    }

    public static Category createChildCategory(AddCategoryRequestDto request, Category parentCategory) {
        return Category.builder()
                .categoryName(request.getCategoryName())
                .categoryCode(UuidGenerator.generateCategoryCode())
                .parent(parentCategory)
                .depth(parentCategory.getDepth() + 1)
                .build();
    }
}
