package org.example.sivillage.member.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.dto.in.ShippingAddressRequestDto;
import org.example.sivillage.member.dto.in.SizeInfoRequestDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_address_id")
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
    private String postalCode;

    @Column(nullable = false)
    private boolean defaultAddress;

    @Column(nullable = false)
    private String memberUuid;


    @Builder
    public ShippingAddress(String addressName, String recipient, String phone, String address, String detailedAddress, String postalCode, boolean defaultAddress, String memberUuid) {
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
    @Builder
    public static ShippingAddress toEntity(ShippingAddressRequestDto dto, String memberUuid) {
        return ShippingAddress.builder()
                .addressName(dto.getAddressName())
                .recipient(dto.getRecipient())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .detailedAddress(dto.getDetailedAddress())
                .postalCode(dto.getPostalCode())
                .defaultAddress(dto.isDefaultAddress())
                .memberUuid(memberUuid)
                .build();
    }


    public void change(ShippingAddressRequestDto dto){
        this.addressName = dto.getAddressName();
        this.recipient = dto.getRecipient();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        this.detailedAddress = dto.getDetailedAddress();
        this.defaultAddress = dto.isDefaultAddress();
    }

}


