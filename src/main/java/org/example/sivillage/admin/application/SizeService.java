package org.example.sivillage.admin.application;

import org.example.sivillage.admin.dto.in.AddSizeRequestDto;
import org.example.sivillage.admin.dto.in.ChangeSizeRequestDto;
import org.example.sivillage.admin.dto.out.GetSizeResponseDto;

import java.util.List;

public interface SizeService {

    /**
     * 1. 사이즈 추가
     * 2. 사이즈 수정
     * 3. 사이즈 삭제
     * 4. 사이즈 조회
     *
     */

    /**
     * 사이즈 추가
     * @param addSizeRequestDto 사이즈 추가 요청 DTO
     */
    void addSize(AddSizeRequestDto addSizeRequestDto);

    /**
     * 사이즈 조회
     * @param id 사이즈 ID
     * @return 사이즈 조회 응답 DTO
     */
    GetSizeResponseDto getSize(Long id);

    /**
     * 사이즈 수정
     * @param changeSizeRequestDto 사이즈 수정 요청 DTO
     */
    void changeSize(ChangeSizeRequestDto changeSizeRequestDto);

    /**
     * 사이즈 삭제
     * @param id 사이즈 ID
     */
    void removeSize(Long id);

    /**
     * 사이즈 조회
     * @param type 사이즈 타입
     * @return 사이즈 조회 응답 DTO 리스트
     */
    List<GetSizeResponseDto> getSizeListByType(String type);
}
