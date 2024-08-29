package org.example.sivillage.member.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_ address_id")
    private Long shippingAddressId;

    @Column(nullable = false)
    private String addressName;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String detailedAddress;

    @Column(nullable = false)
    private String defaultAddress;

    @Column(nullable = false)
    private String memberUuid;

}
