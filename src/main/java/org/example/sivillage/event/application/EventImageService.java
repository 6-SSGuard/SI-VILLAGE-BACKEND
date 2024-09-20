package org.example.sivillage.event.application;
import org.example.sivillage.event.dto.in.EventImageRequestDto;
import org.example.sivillage.event.dto.out.EventImageResponseDto;

import java.util.List;

public interface EventImageService {

    void addEventImage(List<EventImageRequestDto> eventImageRequestDto, Long eventId);
    List<EventImageResponseDto> getEventImage(Long eventId);
    EventImageResponseDto getEventThumbnailImage(Long eventId);
    void removeEventImage(Long eventImageId);

}
