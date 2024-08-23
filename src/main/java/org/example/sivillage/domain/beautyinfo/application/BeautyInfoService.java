package org.example.sivillage.domain.beautyinfo.application;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.domain.beautyinfo.domain.BeautyInfo;
import org.example.sivillage.domain.beautyinfo.dto.BeautyInfoRequest;
import org.example.sivillage.domain.beautyinfo.infrastructure.BeautyInfoRepository;
import org.example.sivillage.domain.beautyinfo.vo.BeautyInfoRequestVo;
import org.example.sivillage.domain.member.domain.Member;
import org.example.sivillage.domain.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BeautyInfoService {

    private final BeautyInfoRepository beautyInfoRepository;
    private final MemberRepository memberRepository;

    public void createBeautyInfo(BeautyInfoRequestVo vo, String memberId) {
        Member member = memberRepository.findByEmail(memberId).orElseThrow();
        BeautyInfoRequest requestDto = BeautyInfoRequest.toDto(vo, member); // vo -> dto
        beautyInfoRepository.save(BeautyInfo.toEntity(requestDto)); // db 저장;
    }


}

