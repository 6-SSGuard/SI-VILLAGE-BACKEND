package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryCode(String categoryCode);

    List<Category> findByParent(Category parent);

    List<Category> findByParentIsNull();
}
