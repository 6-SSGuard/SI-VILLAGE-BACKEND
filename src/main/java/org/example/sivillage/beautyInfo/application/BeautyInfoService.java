package org.example.sivillage.beautyInfo.application;

import org.example.sivillage.beautyInfo.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.beautyInfo.dto.out.BeautyInfoResponseDto;

public interface BeautyInfoService {
    void addBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid);
    BeautyInfoResponseDto getBeautyInfo(String memberUuid);
    void changeBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid);
    void removeBeautyInfo(String memberUuid);
}
