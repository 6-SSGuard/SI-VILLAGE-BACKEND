package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.TopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopCategoryRepository extends JpaRepository <TopCategory, Long> {

    Optional<TopCategory> findByCategoryCode(String categoryCode);
}
