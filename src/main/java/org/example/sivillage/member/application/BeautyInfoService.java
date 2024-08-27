package org.example.sivillage.member.application;


import lombok.RequiredArgsConstructor;

import org.example.sivillage.member.domain.BeautyInfo;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.member.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.member.infrastructure.BeautyInfoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BeautyInfoService {

    private final BeautyInfoRepository beautyInfoRepository;

    public void addBeautyInfo(BeautyInfoRequestDto dto, String memberUuid) {
        Optional<BeautyInfo> beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid);
        if (beautyInfo.isEmpty()) {
            beautyInfoRepository.save(BeautyInfo.toEntity(dto, memberUuid));
        } else {
            throw new RuntimeException("이미 존재하는 뷰티 정보입니다.");
        }
    }

    public BeautyInfoResponseDto getBeautyInfo(String memberUuid) {
        Optional<BeautyInfo> beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid);
        return BeautyInfoResponseDto.toDto(beautyInfo.orElseThrow(() -> new RuntimeException("뷰티 정보가 등록되어 있지 않습니다. 뷰티 정보를 먼저 생성해주세요.")));
    }

    public void changeBeautyInfo(BeautyInfoRequestDto dto, String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new RuntimeException("뷰티 정보가 등록되어 있지 않습니다. 뷰티 정보를 먼저 생성해주세요."));
        beautyInfo.change(dto); // entity로 변환
        beautyInfoRepository.save(beautyInfo);
    }

    public void removeBeautyInfo(String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new RuntimeException("뷰티 정보가 등록되어 있지 않습니다. 뷰티 정보를 먼저 생성해주세요."));
        beautyInfoRepository.delete(beautyInfo);

    }

}

