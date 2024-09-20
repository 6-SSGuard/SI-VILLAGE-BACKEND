package org.example.sivillage.event.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.event.domain.Event;
import org.example.sivillage.event.dto.in.EventRequestDto;
import org.example.sivillage.event.dto.out.EventResponseDto;
import org.example.sivillage.event.infrastructure.EventRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public List<IdListResponseDto<Long>> getEventIdsByCategory(String categoryCode) {
        return eventRepository.findByCategoryCode(categoryCode)
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    public List<IdListResponseDto<Long>> getMemberEventIds(String memberUuid) {
        return eventRepository.findIdByMemberUuid(memberUuid)
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    public EventResponseDto getEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return EventResponseDto.from(event);
    }

    public Long addEvent(EventRequestDto eventRequestDto, String memberUuid) {
        Event event = eventRepository.save(EventRequestDto.toEntity(eventRequestDto, memberUuid));
        return event.getId();

    }

    public void changeEvent(EventRequestDto eventRequestDto, Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EVENT_NOT_FOUND));
        eventRepository.save(eventRequestDto.updateToEntity(eventRequestDto, event));
    }

    public void removeEvent(Long eventId) {
        eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EVENT_NOT_FOUND));
        eventRepository.deleteById(eventId);
    }
}
