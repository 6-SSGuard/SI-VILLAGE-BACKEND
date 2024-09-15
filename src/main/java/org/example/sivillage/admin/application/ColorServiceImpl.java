package org.example.sivillage.admin.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.Color;
import org.example.sivillage.admin.dto.in.AddColorRequestDto;
import org.example.sivillage.admin.dto.in.ChangeColorRequestDto;
import org.example.sivillage.admin.dto.out.GetColorResponseDto;
import org.example.sivillage.admin.infrastructure.ColorRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public void addColor(AddColorRequestDto addColorRequestDto) {
        colorRepository.save(addColorRequestDto.toEntity());
    }

    @Override
    public void changeColor(ChangeColorRequestDto changeColorRequestDto) {
        Color color = colorRepository.findByColorCode(changeColorRequestDto.getColorCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_COLOR));

        colorRepository.save(changeColorRequestDto.updateEntity(color.getId()));
    }

    @Override
    public void removeColor(Long id) {
        colorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public GetColorResponseDto getColor(Long id) {
        return GetColorResponseDto.from(colorRepository.findById(id).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NOT_FOUND_COLOR)
        ));
    }
}
