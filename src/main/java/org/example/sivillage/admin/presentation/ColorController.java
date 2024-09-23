package org.example.sivillage.admin.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.ColorService;
import org.example.sivillage.admin.dto.in.AddColorRequestDto;
import org.example.sivillage.admin.dto.in.ChangeColorRequestDto;
import org.example.sivillage.admin.dto.out.GetColorResponseDto;
import org.example.sivillage.admin.vo.in.AddColorRequestVo;
import org.example.sivillage.admin.vo.in.ChangeColorRequestVo;
import org.example.sivillage.admin.vo.out.GetColorResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "색상 관리 API", description = "색상 관련 API endpoints")
@RequestMapping("/api/color/admin")
public class ColorController {

    /**
     * 1. 색상 추가
     * 2. 색상 수정
     * 3. 색상 삭제
     * 4. 색상 조회
     *
     */

    private final ColorService colorService;

    /**
     * 색상 추가
     * @param addColorRequestVo 색상 추가 요청 VO
     * @return BaseResponse<Void>
     */
    @Operation(summary = "색상 추가 API", description = "색상을 추가합니다.")
    @PostMapping(value = "/")
    public BaseResponse<Void> addColor(@RequestBody AddColorRequestVo addColorRequestVo) {
        colorService.addColor(AddColorRequestDto.from(addColorRequestVo));
        return new BaseResponse<>();
    }

    /**
     * 색상 수정
     * @param changeColorRequestVo 색상 수정 요청 VO
     * @return BaseResponse<Void>
     */
    @Operation(summary = "색상 수정 API", description = "색상을 수정합니다.")
    @PutMapping(value = "/")
    public BaseResponse<Void> changeColor(@RequestBody ChangeColorRequestVo changeColorRequestVo) {
        colorService.changeColor(ChangeColorRequestDto.from(changeColorRequestVo));
        return new BaseResponse<>();
    }

    /**
     * 색상 삭제
     * @param id 색상 ID
     * @return BaseResponse<Void>
     */
    @Operation(summary = "색상 삭제 API", description = "색상을 삭제합니다.")
    @DeleteMapping(value = "/{id}")
    public BaseResponse<Void> removeColor(@PathVariable Long id) {
        colorService.removeColor(id);
        return new BaseResponse<>();
    }

    /**
     * 색상 조회
     * @param id 색상 ID
     * @return BaseResponse<GetColorResponseVo>
     */
    @Operation(summary = "색상 조회 API", description = "색상을 조회합니다.")
    @GetMapping(value = "/{id}")
    public BaseResponse<GetColorResponseVo> getColor(@PathVariable Long id) {
        return new BaseResponse<>(
                colorService.getColor(id).toVo()
        );
    }

    /**
     * 색상 목록 조회
     * @return BaseResponse<List<GetColorResponseVo>>
     */
    @Operation(summary = "색상 목록 조회 API", description = "색상 목록을 조회합니다.")
    @GetMapping(value = "/")
    public BaseResponse<List<GetColorResponseVo>> getColorList() {
        return new BaseResponse<>(
                colorService.getColorList().stream()
                        .map(GetColorResponseDto::toVo)
                        .toList()
        );
    }
    //TODO: 조회 api 하나로 합치기. 단건 조회, 전체 조회, 색상 코드별 조회
}
