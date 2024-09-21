package org.example.sivillage.vendor.infrastructure;

import org.example.sivillage.vendor.domain.ProductByVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductByVendorRepository extends JpaRepository<ProductByVendor, Long> {
    List<ProductByVendor> findByVendorName(String vendorName);

    Optional<ProductByVendor> findByProductCode(String productCode);

    Optional<Double> findDiscountRateByProductCode(String productCode);
}
