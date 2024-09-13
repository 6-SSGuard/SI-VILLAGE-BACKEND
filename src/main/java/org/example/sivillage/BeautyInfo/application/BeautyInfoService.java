package org.example.sivillage.BeautyInfo.application;

import org.example.sivillage.BeautyInfo.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.BeautyInfo.dto.out.BeautyInfoResponseDto;

public interface BeautyInfoService {
    void addBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid);
    BeautyInfoResponseDto getBeautyInfo(String memberUuid);
    void changeBeautyInfo(BeautyInfoRequestDto beautyInfoRequestDto, String memberUuid);
    void removeBeautyInfo(String memberUuid);
}
