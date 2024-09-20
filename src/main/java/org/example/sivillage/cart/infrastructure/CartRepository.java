package org.example.sivillage.cart.infrastructure;

import org.example.sivillage.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c.id FROM Cart c WHERE c.memberUuid = :memberUuid")
    List<Long> findByMemberUuid(@Param("memberUuid") String memberUuid);
    Integer countByMemberUuid(String memberUuid);
    Optional<Cart> findByMemberUuidAndProductCodeAndProductOption(String memberUuid, String productCode, String productOption);

}
