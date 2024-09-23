package org.example.sivillage.auth.vo.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.auth.dto.in.AuthCodeRequestDto;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodeRequestVo {

private String authCode;


    public static AuthCodeRequestDto toDto(AuthCodeRequestVo authCodeRequestVo) {
        return AuthCodeRequestDto.builder()
                .authCode(authCodeRequestVo.authCode)
                .build();
    }

}
