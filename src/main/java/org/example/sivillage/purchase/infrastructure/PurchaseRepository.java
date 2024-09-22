package org.example.sivillage.purchase.infrastructure;

import org.example.sivillage.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findByPurchaseCode(String purchaseCode);
}
