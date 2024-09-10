package org.example.sivillage.BeautyInfo.application;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.BeautyInfo.domain.BeautyInfo;
import org.example.sivillage.BeautyInfo.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.BeautyInfo.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.BeautyInfo.infrastructure.BeautyInfoRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class BeautyInfoServiceImpl implements BeautyInfoService {

    private final BeautyInfoRepository beautyInfoRepository;

    public void addBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElse(beautyInfoRepository.save(beautyInfoRequestDto.toEntity(beautyInfoRequestDto,memberUuid)));
        }

    public BeautyInfoResponseDto getBeautyInfo(String memberUuid) {
        return beautyInfoRepository.findByMemberUuid(memberUuid)
                .map(BeautyInfoResponseDto::from) // BeautyInfo가 존재하는 경우 DTO로 변환
                .orElseGet(BeautyInfoResponseDto::emptyResponse); // 없는 경우 emptyResponse 반환
    }

    public void changeBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid) {
//        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_BEAUTY_INFO));
//        beautyInfoRepository.save(beautyInfoRequestDto.updateToEntity(beautyInfoRequestDto,beautyInfo));
    }

    public void removeBeautyInfo(String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_BEAUTY_INFO));
        beautyInfoRepository.delete(beautyInfo);

    }

}

