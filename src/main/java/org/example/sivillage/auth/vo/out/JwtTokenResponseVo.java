package org.example.sivillage.auth.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JwtTokenResponseVo {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String memberUuid;

    @Builder
    public JwtTokenResponseVo(String grantType, String accessToken, String refreshToken, String memberUuid) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.memberUuid = memberUuid;
    }
}
