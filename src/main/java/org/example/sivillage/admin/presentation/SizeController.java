package org.example.sivillage.admin.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.SizeService;
import org.example.sivillage.admin.dto.in.AddSizeRequestDto;
import org.example.sivillage.admin.dto.in.ChangeSizeRequestDto;
import org.example.sivillage.admin.dto.out.GetSizeResponseDto;
import org.example.sivillage.admin.vo.in.AddSizeRequestVo;
import org.example.sivillage.admin.vo.in.ChangeSizeRequestVo;
import org.example.sivillage.admin.vo.out.GetSizeResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "사이즈 관리 API", description = "사이즈 관련 API endpoints")
@RequestMapping("/api/size/admin")
public class SizeController {

    private final SizeService sizeService;

    @Operation(summary = "사이즈 추가 API", description = "사이즈를 추가합니다.")
    @PostMapping(value = "/")
    public BaseResponse<Void> addSize(@RequestBody AddSizeRequestVo addSizeRequestVo) {
        sizeService.addSize(AddSizeRequestDto.from(addSizeRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "사이즈 조회 API", description = "사이즈를 조회합니다.")
    @GetMapping(value = "/{id}")
    public BaseResponse<GetSizeResponseVo> getSize(@PathVariable Long id) {
        return new BaseResponse<>(
                sizeService.getSize(id).toVo()
        );
    }

    @Operation(summary = "사이즈 수정 API", description = "사이즈를 수정합니다.")
    @PutMapping(value = "/")
    public BaseResponse<Void> changeSize(@RequestBody ChangeSizeRequestVo changeSizeRequestVo) {
        sizeService.changeSize(ChangeSizeRequestDto.from(changeSizeRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "사이즈 삭제 API", description = "사이즈를 삭제합니다.")
    @DeleteMapping(value = "/{id}")
    public BaseResponse<Void> removeSize(@PathVariable Long id) {
        sizeService.removeSize(id);
        return new BaseResponse<>();
    }

    @Operation(summary = "사이즈 목록 조회 API", description = "사이즈 목록을 조회합니다.")
    @GetMapping(value = "/type")
    public BaseResponse<List<GetSizeResponseVo>> getSizeListByType(String type) {
        return new BaseResponse<>(
                sizeService.getSizeListByType(type).stream()
                        .map(GetSizeResponseDto::toVo)
                        .toList()
        );
    }
}
