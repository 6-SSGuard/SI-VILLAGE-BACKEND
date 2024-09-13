package org.example.sivillage.sizeinfo.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.sizeinfo.domain.SizeInfo;
import org.example.sivillage.sizeinfo.dto.in.SizeInfoRequestDto;
import org.example.sivillage.sizeinfo.dto.out.SizeInfoResponseDto;
import org.example.sivillage.sizeinfo.infrastructure.SizeInfoRepository;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class SizeInfoServiceImpl implements SizeInfoService {

    private final SizeInfoRepository sizeInfoRepository;

    public void addSizeInfo(SizeInfoRequestDto sizeInfoRequestDto, String memberUuid) {
        sizeInfoRepository.findByMemberUuid(memberUuid).ifPresent(sizeInfo -> {
            throw new BaseException(BaseResponseStatus.DUPLICATE_SIZE_INFO);
        });
        sizeInfoRepository.save(sizeInfoRequestDto.toEntity(sizeInfoRequestDto, memberUuid));
    }

    public SizeInfoResponseDto getSizeInfo(String memberUuid) {
        return sizeInfoRepository.findByMemberUuid(memberUuid)
                .map(SizeInfoResponseDto::from).
                orElseGet(SizeInfoResponseDto::emptyResponse);
    }

    public void changeSizeInfo(SizeInfoRequestDto sizeInfoRequestDto, String memberUuid) {
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO));
        sizeInfoRepository.save(sizeInfoRequestDto.updateToEntity(sizeInfoRequestDto, sizeInfo));
    }

    public void removeSizeInfo(String memberUuid) {
        SizeInfo sizeInfo = sizeInfoRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SIZE_INFO));
        sizeInfoRepository.delete(sizeInfo);

    }

}



