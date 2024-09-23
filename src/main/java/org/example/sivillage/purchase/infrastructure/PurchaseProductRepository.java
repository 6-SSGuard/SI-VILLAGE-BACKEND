package org.example.sivillage.purchase.infrastructure;

import org.example.sivillage.purchase.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
    List<PurchaseProduct> findByPurchaseCode(String purchaseCode);

    List<PurchaseProduct> findAllByPurchaseCode(String purchaseCode);
}
