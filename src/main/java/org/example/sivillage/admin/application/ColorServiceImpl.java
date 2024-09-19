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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    /**
     * 색상 추가
     * @param addColorRequestDto 색상 추가 요청 DTO
     */
    @Override
    public void addColor(AddColorRequestDto addColorRequestDto) {
        colorRepository.save(addColorRequestDto.toEntity());
    }

    /**
     * 색상 변경
     * @param changeColorRequestDto 색상 변경 요청 DTO
     */
    @Override
    public void changeColor(ChangeColorRequestDto changeColorRequestDto) {
        Color color = colorRepository.findByColorCode(changeColorRequestDto.getColorCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_COLOR));

        colorRepository.save(changeColorRequestDto.updateEntity(color.getId()));
    }

    /**
     * 색상 삭제
     * @param id 색상 ID
     */
    @Override
    public void removeColor(Long id) {
        colorRepository.deleteById(id);
    }

    /**
     * 색상 조회
     * @param id 색상 ID
     * @return 색상 조회 응답 DTO
     */
    @Transactional(readOnly = true)
    @Override
    public GetColorResponseDto getColor(Long id) {
        return GetColorResponseDto.from(colorRepository.findById(id).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NOT_FOUND_COLOR)
        ));
    }

    @Override
    public List<GetColorResponseDto> getColorList() {

        return colorRepository.findAll().stream()
                .map(GetColorResponseDto::from)
                .toList();
    }
}
