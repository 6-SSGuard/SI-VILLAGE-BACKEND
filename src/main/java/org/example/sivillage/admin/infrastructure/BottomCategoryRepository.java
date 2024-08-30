package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.BottomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BottomCategoryRepository extends JpaRepository<BottomCategory, Long> {
    Optional<BottomCategory> findByCategoryCode(String bottomCategoryCode);
}
