package org.example.sivillage.event.application;

import org.example.sivillage.event.dto.in.EventRequestDto;
import org.example.sivillage.event.dto.out.EventResponseDto;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;

import java.util.List;

public interface EventService {
    Long addEvent(EventRequestDto eventRequestDto, String memberUuid);
    List<IdListResponseDto<Long>> getEventIdsByCategory(String categoryCode); // 카테고리 코드 넣으면 그에 맞는 이벤트 id 값 던져주기
    List<IdListResponseDto<Long>> getMemberEventIds(String memberUuid);
    EventResponseDto getEvent(Long eventId);
    void changeEvent(EventRequestDto eventRequestDto, Long eventId);
    void removeEvent(Long eventId);

}
