package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String productCode);

    @Query("SELECT p.productCode FROM Product p")
    List<String> findAllProductCode();


}