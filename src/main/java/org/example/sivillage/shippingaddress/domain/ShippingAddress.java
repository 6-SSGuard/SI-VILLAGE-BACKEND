package org.example.sivillage.shippingaddress.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String postalCode;

    @Column(nullable = false)
    private boolean defaultAddress;

    @Column(nullable = false)
    private String memberUuid;


    @Builder
    public ShippingAddress(Long id, String addressName, String recipient, String phone, String address, String detailedAddress, String postalCode, boolean defaultAddress, String memberUuid) {
        this.id = id;
        this.addressName = addressName;
        this.recipient = recipient;
        this.phone = phone;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.postalCode = postalCode;
        this.defaultAddress = defaultAddress;
        this.memberUuid = memberUuid;
    }

    // 기본 배송지 상태를 활성화하는 메소드
    public void activateAsDefault() {
        this.defaultAddress = true;
    }

    // 기본 배송지 상태를 비활성화하는 메소드
    public void deactivateAsDefault() {
        this.defaultAddress = false;
    }

}


