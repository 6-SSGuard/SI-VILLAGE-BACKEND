package org.example.sivillage.admin.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.Size;
import org.example.sivillage.admin.dto.in.AddSizeRequestDto;
import org.example.sivillage.admin.dto.in.ChangeSizeRequestDto;
import org.example.sivillage.admin.dto.out.GetSizeResponseDto;
import org.example.sivillage.admin.infrastructure.SizeRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public void addSize(AddSizeRequestDto addSizeRequestDto) {
        sizeRepository.save(addSizeRequestDto.toEntity());
    }

    @Override
    public GetSizeResponseDto getSize(Long id) {
        return GetSizeResponseDto.from(sizeRepository.findById(id).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NOT_FOUND_SIZE)));
    }

    @Override
    public void changeSize(ChangeSizeRequestDto changeSizeRequestDto) {
        Size size = sizeRepository.findById(changeSizeRequestDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_SIZE));

        sizeRepository.save(changeSizeRequestDto.updateEntity(size.getId()));
    }

    @Override
    public void removeSize(Long id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public List<GetSizeResponseDto> getSizeListByType(String type) {
        return sizeRepository.findBySizeType(type).stream()
                .map(GetSizeResponseDto::from)
                .toList();
    }
}
