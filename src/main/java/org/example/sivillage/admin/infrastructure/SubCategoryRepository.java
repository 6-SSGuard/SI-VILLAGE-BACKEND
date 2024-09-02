package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.BottomCategory;
import org.example.sivillage.admin.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findByCategoryCode(String subCategoryCode);

    List<SubCategory> findByBottomCategory(BottomCategory bottomCategory);
}
