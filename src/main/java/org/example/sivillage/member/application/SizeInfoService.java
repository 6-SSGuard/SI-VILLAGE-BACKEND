package org.example.sivillage.member.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.domain.BeautyInfo;
import org.example.sivillage.member.domain.SizeInfo;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.member.dto.in.SizeInfoRequestDto;
import org.example.sivillage.member.dto.out.SizeInfoResponseDto;
import org.example.sivillage.member.infrastructure.SizeInfoRepository;
import org.example.sivillage.productoption.domain.Size;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class SizeInfoService {

    private final SizeInfoRepository sizeInfoRepository;

    public void addSizeInfo(SizeInfoRequestDto dto, String memberUuid) {
        Optional<SizeInfo> sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid);
        if (sizeInfo.isEmpty()) {
            sizeInfoRepository.save(SizeInfo.toEntity(dto, memberUuid));
        } else {
            throw new BaseException(BaseResponseStatus.DUPLICATE_SIZE_INFO);
        }
    }

    public SizeInfoResponseDto getSizeInfo(String memberUuid) {
       Optional <SizeInfo> sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid);
        return SizeInfoResponseDto.toDto((sizeInfo.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO))));
    }

    public void changeSizeInfo(SizeInfoRequestDto dto, String memberUuid) {
      SizeInfo sizeInfo =  sizeInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(()->new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO));
        sizeInfo.change(dto); // entity로 변환
        sizeInfoRepository.save(sizeInfo);

    }

    public void removeSizeInfo(String memberUuid) {
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(()->new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO));
        sizeInfoRepository.delete(sizeInfo);

    }

}



