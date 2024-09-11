package org.example.sivillage.auth.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String memberUuid) throws UsernameNotFoundException {
        return new AuthUserDetails(memberRepository.findByMemberUuid(memberUuid).orElseThrow(() -> new UsernameNotFoundException(memberUuid)));
    }

}

