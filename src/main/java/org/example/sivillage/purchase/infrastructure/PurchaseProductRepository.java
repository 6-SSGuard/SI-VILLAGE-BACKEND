package org.example.sivillage.purchase.infrastructure;

import org.example.sivillage.purchase.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
}
