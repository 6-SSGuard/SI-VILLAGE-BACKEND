package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.vo.SignUpRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Builder
    public Member(String email, String password, Role role, String name) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    @Builder
    public static Member createMember(SignUpRequest request, String encodedPassword) {
        return Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(request.getRole())
                .name(request.getName())
                .build();
    }

}
