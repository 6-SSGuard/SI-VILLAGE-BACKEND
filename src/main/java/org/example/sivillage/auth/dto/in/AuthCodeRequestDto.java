package org.example.sivillage.auth.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthCodeRequestDto {

public String authCode;

@Builder
    public AuthCodeRequestDto(String authCode) {
        this.authCode = authCode;
    }

}
