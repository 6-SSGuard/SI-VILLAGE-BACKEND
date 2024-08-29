package org.example.sivillage.member.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressRequestVo {

private String addressName;

private String recipient;

private String phone;

private String address;
// 주소 검색 api 는 프론트에서 연결해서 값을 넘겨주면 그 값을 db에 저장하면 되는건가?

private String addressDetail;

private boolean defaultAddress;

}
