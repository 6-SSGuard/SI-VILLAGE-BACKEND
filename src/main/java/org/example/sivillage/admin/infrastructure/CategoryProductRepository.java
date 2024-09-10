package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
}
