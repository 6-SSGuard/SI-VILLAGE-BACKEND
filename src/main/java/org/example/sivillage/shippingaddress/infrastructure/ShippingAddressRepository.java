package org.example.sivillage.shippingaddress.infrastructure;

import org.example.sivillage.shippingaddress.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository <ShippingAddress, Long>{
    Optional<ShippingAddress>findByMemberUuid(String memberUuid);
    Optional<ShippingAddress> findByMemberUuidAndDefaultAddress(String memberUuid, boolean defaultAddress);
    Optional<ShippingAddress>findByMemberUuidAndId(String memberUuid, Long shippingAddressId);
    List<ShippingAddress> findAllByMemberUuid(String memberUuid);


    }
