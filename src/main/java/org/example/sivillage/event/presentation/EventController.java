package org.example.sivillage.event.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.event.application.EventImageServiceImpl;
import org.example.sivillage.event.application.EventProductServiceImpl;
import org.example.sivillage.event.application.EventServiceImpl;
import org.example.sivillage.event.dto.in.EventImageRequestDto;
import org.example.sivillage.event.dto.out.EventImageResponseDto;
import org.example.sivillage.event.dto.out.EventResponseDto;
import org.example.sivillage.event.vo.in.EventImageRequestVo;
import org.example.sivillage.event.vo.in.EventProductRequestVo;
import org.example.sivillage.event.vo.in.EventRequestVo;
import org.example.sivillage.event.vo.out.EventImageResponseVo;
import org.example.sivillage.event.vo.out.EventResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "이벤트 관리 API", description = "이벤트 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventServiceImpl eventService;
    private final EventImageServiceImpl eventImageService;
    private final EventProductServiceImpl eventProductService;

    @Operation(summary = "카테고리별 이벤트id 조회", description = "카테고리별 이벤트 id 리스트를 반환")
    @GetMapping("/category/{categoryCode}")
    public BaseResponse<List<IdListResponseVo<Long>>> getCategoryEventIds(@PathVariable("categoryCode") String categoryCode) {
        List<IdListResponseVo<Long>> idListResponseVoList = eventService.getEventIdsByCategory(categoryCode).stream().map(IdListResponseDto::toVo).toList();
        return new BaseResponse<>(idListResponseVoList);
    }


    @Operation(summary = "회원의 이벤트id 조회", description = "회원(벤더)가 등록한 이벤트 id 리스트를 반환")
    @GetMapping("/{memberUuid}")
    public BaseResponse<List<IdListResponseVo<Long>>> getMemberEventIds(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        List<IdListResponseVo<Long>> idListResponseVoList = eventService.getMemberEventIds(authUserDetails.getMemberUuid())
                .stream()
                .map(IdListResponseDto::toVo).toList();
        return new BaseResponse<>(idListResponseVoList);
    }

    @Operation(summary = "이벤트 등록", description = "이벤트를 등록합니다.")
    @PostMapping("")
    public BaseResponse<Long> addEvent(@RequestBody EventRequestVo eventRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        System.out.println(eventRequestVo.getBrand());
        Long eventId = eventService.addEvent(EventRequestVo.toDto(eventRequestVo), authUserDetails.getMemberUuid());
        return new BaseResponse<>(eventId);
    }

    @Operation(summary = "이벤트 조회", description = "이벤트를 조회합니다.")
    @GetMapping("get/{eventId}")
    public BaseResponse<EventResponseVo> getEvent(@PathVariable("eventId") Long eventId) {
        EventResponseDto eventResponseDto = eventService.getEvent(eventId);
        return new BaseResponse<>(eventResponseDto.toResponseVo());
    }

    @Operation(summary = "이벤트 수정", description = "이벤트를 수정합니다.")
    @PutMapping("/{eventId}")
    public BaseResponse<Void> updateEvent(@PathVariable("eventId") Long eventId, @RequestBody EventRequestVo eventRequestVo) {
        eventService.changeEvent(EventRequestVo.toDto(eventRequestVo), eventId);
        return new BaseResponse<>();
    }

    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제합니다.")
    @DeleteMapping("/{eventId}")
    public BaseResponse<Void> removeEvent(@PathVariable("eventId") Long eventId) {
        eventService.removeEvent(eventId);
        return new BaseResponse<>();
    }


    // 이벤트 이미지 관련 API

    @Operation(summary = "이벤트 이미지 등록", description = "이벤트 이미지를 등록합니다.")
    @PostMapping("/{eventId}/images")
    public BaseResponse<List<String>> addEventImages(@PathVariable("eventId") Long eventId, @Valid @RequestBody List<EventImageRequestVo> eventImageRequestVo) {
    @PostMapping("/vendor/{eventId}/images")
    public BaseResponse<Void> addEventImages(@PathVariable("eventId") Long eventId, @Valid @RequestBody List<EventImageRequestVo> eventImageRequestVo) {
        List<EventImageRequestDto> eventImageRequestDtoList = eventImageRequestVo.stream()
                .map(EventImageRequestVo::toDto)
                .toList();

        eventImageService.addEventImage(eventImageRequestDtoList, eventId);
        return new BaseResponse<>();
    }

    @Operation(summary = "이벤트 이미지 조회", description = "이벤트 이미지를 조회합니다.")
    @GetMapping("/{eventId}/images")
    public BaseResponse<List<EventImageResponseVo>> getEventImages(@PathVariable("eventId") Long eventId) {
        List<EventImageResponseVo> eventImageResponseVoList = eventImageService.getEventImage(eventId)
                .stream()
                .map(EventImageResponseDto::toResponseVo)
                .toList();
        return new BaseResponse<>(eventImageResponseVoList);
    }

    @Operation(summary = "이벤트 썸네일 이미지 조회", description = "이벤트 썸네일 이미지를 조회합니다.")
    @GetMapping("/{eventId}/thumbnail")
    public BaseResponse<EventImageResponseVo> getEventThumbnailImages(@PathVariable("eventId") Long eventId) {
        EventImageResponseDto eventImageResponseDto = eventImageService.getEventThumbnailImage(eventId);
        return new BaseResponse<>(eventImageResponseDto.toResponseVo());

    }

    @Operation(summary = "이벤트 이미지 삭제", description = "이벤트 이미지를 삭제합니다.")
    @DeleteMapping("/{eventImageId}/images")
    public BaseResponse<Void> removeEventImage(@PathVariable("eventImageId") Long eventImageId) {
        eventImageService.removeEventImage(eventImageId);
        return new BaseResponse<>();
    }

    // 상품 이벤트 등록 관련 API

    @Operation(summary = "이벤트 해당 상품코드 조회", description = "이벤트에 해당하는 상품코드를 조회합니다.")
    @GetMapping("/{eventId}/products")
    public BaseResponse<List<IdListResponseVo<String>>> getEventProductCodes(@PathVariable("eventId") Long eventId) {
        List<IdListResponseVo<String>> idListResponseVoList = eventProductService.getEventProductCode(eventId)
                .stream()
                .map(IdListResponseDto::toVo)
                .toList();
        return new BaseResponse<>(idListResponseVoList);
    }

    @Operation(summary = "상품 이벤트 등록", description = "이벤트에 상품을 등록합니다.")
    @PostMapping("/{eventId}/products")
    public BaseResponse<Void> addEventProduct(@PathVariable("eventId") Long eventId, @RequestBody EventProductRequestVo eventProductRequestVo) {
        eventProductService.addEventProduct(eventId, eventProductRequestVo.toDto(eventProductRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 이벤트 삭제", description = "이벤트에서 상품을 삭제합니다.")
    @DeleteMapping("/products")
    public BaseResponse<Void> removeEventProduct(@RequestBody EventProductRequestVo eventProductRequestVo) {
        eventProductService.removeEventProduct(eventProductRequestVo.toDto(eventProductRequestVo));
        return new BaseResponse<>();
    }
}