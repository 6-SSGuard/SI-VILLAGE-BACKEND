package org.example.sivillage.event.infrastructure;

import org.example.sivillage.event.domain.EventProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventProductRepository extends JpaRepository<EventProduct, Long> {

    @Query("SELECT e.productCode FROM EventProduct e WHERE e.eventId = :eventId")
    List<String> findProductCodesByEventId(Long eventId);

    Optional<EventProduct> findByProductCode(String productCode);
}
