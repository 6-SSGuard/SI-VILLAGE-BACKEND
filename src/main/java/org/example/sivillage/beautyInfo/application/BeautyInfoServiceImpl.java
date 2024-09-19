package org.example.sivillage.beautyInfo.application;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.beautyInfo.domain.BeautyInfo;
import org.example.sivillage.beautyInfo.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.beautyInfo.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.beautyInfo.infrastructure.BeautyInfoRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BeautyInfoServiceImpl implements BeautyInfoService {

    private final BeautyInfoRepository beautyInfoRepository;

    public void addBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid) {
        beautyInfoRepository.findByMemberUuid(memberUuid)
                .ifPresent(BeautyInfo -> {
                    throw new BaseException(BaseResponseStatus.DUPLICATE_BEAUTY_INFO);
                });
        beautyInfoRepository.save(beautyInfoRequestDto.toEntity(beautyInfoRequestDto, memberUuid));
    }

    public BeautyInfoResponseDto getBeautyInfo(String memberUuid) {
        return beautyInfoRepository.findByMemberUuid(memberUuid)
                .map(BeautyInfoResponseDto::from)
                .orElseGet(BeautyInfoResponseDto::defaultResponse);
    }

    public void changeBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_BEAUTY_INFO));
        beautyInfoRepository.save(beautyInfoRequestDto.updateToEntity(beautyInfoRequestDto, beautyInfo));
    }

    public void removeBeautyInfo(String memberUuid) {
        BeautyInfo beautyInfo = beautyInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_BEAUTY_INFO));
        beautyInfoRepository.delete(beautyInfo);

    }

}

