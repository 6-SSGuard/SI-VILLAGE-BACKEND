package org.example.sivillage.auth.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JwtTokenRequestVo {

    private String email;
    private String password;
}
