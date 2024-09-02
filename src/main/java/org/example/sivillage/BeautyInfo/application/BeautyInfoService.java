package org.example.sivillage.BeautyInfo.application;


import lombok.RequiredArgsConstructor;

import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;
import org.example.sivillage.BeautyInfo.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.BeautyInfo.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.BeautyInfo.infrastructure.BeautyInfoRepository;
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
            throw new BaseException(BaseResponseStatus.DUPLICATE_BEAUTY_INFO);
        }
    }

    public BeautyInfoResponseDto getBeautyInfo(String memberUuid) {
        Optional<BeautyInfo> beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid);
        return BeautyInfoResponseDto.toDto(beautyInfo.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_BEAUTY_INFO)));
    }

    public void changeBeautyInfo(BeautyInfoRequestDto dto, String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_BEAUTY_INFO));
        beautyInfo.change(dto); // entity로 변환
        beautyInfoRepository.save(beautyInfo);
    }

    public void removeBeautyInfo(String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_BEAUTY_INFO));
        beautyInfoRepository.delete(beautyInfo);

    }

}

