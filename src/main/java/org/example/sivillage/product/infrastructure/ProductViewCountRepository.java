package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductViewCountRepository extends JpaRepository<ProductViewCount,Long> {
}
