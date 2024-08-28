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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class SizeInfoService {

    private final SizeInfoRepository sizeInfoRepository;
    private final ModelMapper modelMapper;

    public void addSizeInfo(SizeInfoRequestDto dto, String memberUuid) {
        Optional<SizeInfo> sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid);
        log.info("Converted SizeInfoRequestDto: {}", dto);
        if (sizeInfo.isEmpty()) {
            sizeInfoRepository.save(SizeInfo.toEntity(dto, memberUuid));
        } else {
            throw new BaseException(BaseResponseStatus.DUPLICATE_SIZE_INFO);
        }
    }

    public SizeInfoResponseDto getSizeInfo(String memberUuid) {
       SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid)
               .orElseThrow(()->new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO));
       return modelMapper.map(sizeInfo, SizeInfoResponseDto.class);
    }

    public void changeSizeInfo(SizeInfoRequestDto dto, String memberUuid) {
      SizeInfo sizeInfo =  sizeInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(()->new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO));
        modelMapper.map(dto, sizeInfo);
        sizeInfoRepository.save(sizeInfo);

    }

    public void removeSizeInfo(String memberUuid) {
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(()->new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO));
        sizeInfoRepository.delete(sizeInfo);

    }

}



