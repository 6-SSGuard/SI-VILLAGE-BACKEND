package org.example.sivillage.sizeinfo.application;

import org.example.sivillage.sizeinfo.dto.in.SizeInfoRequestDto;
import org.example.sivillage.sizeinfo.dto.out.SizeInfoResponseDto;

public interface SizeInfoService {
    void addSizeInfo(SizeInfoRequestDto dto, String memberUuid);
    SizeInfoResponseDto getSizeInfo(String memberUuid);
    void changeSizeInfo(SizeInfoRequestDto dto, String memberUuid);
    void removeSizeInfo(String memberUuid);
}
