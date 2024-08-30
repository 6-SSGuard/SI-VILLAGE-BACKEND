package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.MiddleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Long> {

    Optional<MiddleCategory> findByCategoryCode(String middleCategoryCode);
}
