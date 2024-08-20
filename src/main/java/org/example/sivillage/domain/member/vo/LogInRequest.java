package org.example.sivillage.domain.member.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LogInRequest {

    private String email;

    private String password;
}