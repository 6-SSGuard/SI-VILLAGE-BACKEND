package org.example.sivillage.member.infrastructure;

import org.example.sivillage.member.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository <ShippingAddress, Long>{
    Optional<ShippingAddress>findByMemberUuid(String memberUuid);
    Optional<ShippingAddress>findByMemberUuidAndShippingAddressId(String memberUuid, Long shippingAddressId);
    Optional<ShippingAddress> findByMemberUuidAndDefaultAddress(String memberUuid, boolean defaultAddress);
    List<ShippingAddress> findAllByMemberUuid(String memberUuid);

    }
