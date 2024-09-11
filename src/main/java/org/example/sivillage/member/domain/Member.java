package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.auth.domain.Role;
import org.example.sivillage.auth.vo.SignUpRequest;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberUuid;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date birth;

    @Builder
    public Member(String memberUuid, String email, Role role, String name, String password, Date birth) {
        this.memberUuid = memberUuid;
        this.email = email;
        this.role = role;
        this.name = name;
        this.password = password;
        this.birth = birth;
    }

    @Builder
    public static Member createMember(SignUpRequest request, String memberUuid, String encodedPassword) {
        return Member.builder()
                .memberUuid(memberUuid)
                .email(request.getEmail())
                .password(encodedPassword)
                .role(request.getRole())
                .name(request.getName())
                .build();
    }

}
