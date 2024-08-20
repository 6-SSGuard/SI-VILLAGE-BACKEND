package org.example.sivillage.domain.product.infrastructure;

import org.example.sivillage.domain.product.domain.Product;
import org.example.sivillage.domain.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    Optional<ProductOption> findByProduct(Product product);
}
