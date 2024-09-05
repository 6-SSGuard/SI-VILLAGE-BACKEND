package org.example.sivillage.product.infrastructure;

import org.example.sivillage.brand.domain.BrandProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandProductRepository extends JpaRepository<BrandProduct, Long> {
}
