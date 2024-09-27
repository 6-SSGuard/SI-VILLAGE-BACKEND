package org.example.sivillage.member.initializer;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.auth.domain.Role;
import org.example.sivillage.member.domain.Member;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class MemberInitializer implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int size = 1; size <= 10; size += 1) {
            memberRepository.save(Member.builder()
                    .name("name" + size)
                    .email("user" + size + "@example.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.MEMBER)
                    .memberUuid(String.valueOf(size))
                    .birth(new Date())
                    .build());
        }
    }
}

