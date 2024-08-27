package org.example.sivillage.member.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.error.CustomException;
import org.example.sivillage.global.error.ErrorCode;
import org.example.sivillage.member.domain.BeautyInfo;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.member.infrastructure.infrastructure.BeautyInfoRepository;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BeautyInfoService {

    private final BeautyInfoRepository beautyInfoRepository;
    private final MemberRepository memberRepository;

    public void addBeautyInfo(BeautyInfoRequestDto dto, String memberUuid) {
        Optional<BeautyInfo> beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid);
        if (beautyInfo.isEmpty()) {
            beautyInfoRepository.save(BeautyInfo.toEntity(dto, memberUuid));
        } else {
            throw new CustomException(ErrorCode.DUPLICATE_BEAUTY_INFO);
        }
    }



}

