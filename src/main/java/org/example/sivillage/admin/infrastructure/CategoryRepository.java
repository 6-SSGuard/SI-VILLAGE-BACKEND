package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c.categoryName FROM Category c WHERE c.categoryCode = :categoryCode")
    Optional<String> findCategoryNameByCategoryCode(String categoryCode);
    Optional<Category> findByCategoryCode(String categoryCode);
    List<Category> findByParent(Category parent);

    List<Category> findByParentIsNull();

    Boolean existsByCategoryName(String categoryName);

    Optional<Category> findFirstByCategoryNameOrderByIdAsc(String categoryName);
}
