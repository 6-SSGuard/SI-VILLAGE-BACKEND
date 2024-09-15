package org.example.sivillage.admin.application;

import org.example.sivillage.admin.dto.in.AddColorRequestDto;
import org.example.sivillage.admin.dto.in.ChangeColorRequestDto;
import org.example.sivillage.admin.dto.out.GetColorResponseDto;

import java.util.List;

public interface ColorService {

    /**
     * 1. 색상 추가
     * 2. 색상 수정
     * 3. 색상 삭제
     * 4. 색상 조회
     *
     */

    /**
     * 색상 추가
     * @param addColorRequestDto 색상 추가 요청 DTO
     */
    void addColor(AddColorRequestDto addColorRequestDto);

    /**
     * 색상 수정
     * @param changeColorRequestDto 색상 수정 요청 DTO
     */
    void changeColor(ChangeColorRequestDto changeColorRequestDto);

    /**
     * 색상 삭제
     * @param id 색상 ID
     */
    void removeColor(Long id);

    /**
     * 색상 조회
     * @param id 색상 ID
     * @return 색상 조회 응답 DTO
     */
    GetColorResponseDto getColor(Long id);

    List<GetColorResponseDto> getColorList();
}
