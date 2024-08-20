package org.example.sivillage.domain.brand.infrastructure;

import org.example.sivillage.domain.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String brand);
}
