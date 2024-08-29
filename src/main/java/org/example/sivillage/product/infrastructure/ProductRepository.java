package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductUuid(String productUuid);

    @Query("SELECT p.productUuid FROM Product p")
    List<String> findAllProductUuids();
}