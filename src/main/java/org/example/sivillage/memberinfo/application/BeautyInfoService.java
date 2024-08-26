package org.example.sivillage.memberinfo.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.memberinfo.domain.BeautyInfo;
import org.example.sivillage.memberinfo.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.memberinfo.infrastructure.BeautyInfoRepository;
import org.example.sivillage.memberinfo.vo.BeautyInfoRequestVo;
import org.example.sivillage.member.domain.Member;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BeautyInfoService {

    private final BeautyInfoRepository beautyInfoRepository;
    private final MemberRepository memberRepository;

    public void createBeautyInfo(BeautyInfoRequestVo vo, String memberId) {
        Member member = memberRepository.findByEmail(memberId).orElseThrow();
        BeautyInfoRequestDto requestDto = BeautyInfoRequestDto.toDto(vo, member); // vo -> dto
        beautyInfoRepository.save(BeautyInfo.toEntity(requestDto)); // db 저장;
    }


}

