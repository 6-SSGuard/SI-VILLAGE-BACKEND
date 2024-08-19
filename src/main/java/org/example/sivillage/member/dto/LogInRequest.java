package org.example.sivillage.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LogInRequest {

    private String email;

    private String password;
}