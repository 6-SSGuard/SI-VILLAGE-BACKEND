package org.example.sivillage.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.member.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class PasswordRequestDto {

    private String password;

    public Member changePassword(PasswordEncoder passwordEncoder, Member member, PasswordRequestDto passwordRequestDto) {
        return Member.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(passwordEncoder.encode(passwordRequestDto.getPassword()))
                .memberUuid(member.getMemberUuid())
                .name(member.getName())
                .role(member.getRole())
                .birth(member.getBirth()).build();
    }

    @Builder
    public PasswordRequestDto(String password) {
        this.password = password;
    }

}
