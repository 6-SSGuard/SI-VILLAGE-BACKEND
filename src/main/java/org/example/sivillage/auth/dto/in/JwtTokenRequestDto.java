package org.example.sivillage.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.auth.vo.in.JwtTokenRequestVo;

@Getter
@NoArgsConstructor
public class JwtTokenRequestDto {

    private String email;
    private String password;

    @Builder
    public JwtTokenRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static JwtTokenRequestDto from(JwtTokenRequestVo jwtTokenRequestVo) {
        return JwtTokenRequestDto.builder()
                .email(jwtTokenRequestVo.getEmail())
                .password(jwtTokenRequestVo.getPassword())
                .build();
    }
}
