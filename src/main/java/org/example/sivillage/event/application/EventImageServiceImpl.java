package org.example.sivillage.event.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.event.domain.EventImage;
import org.example.sivillage.event.dto.in.EventImageRequestDto;
import org.example.sivillage.event.dto.out.EventImageResponseDto;
import org.example.sivillage.event.infrastructure.EventImageRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventImageServiceImpl implements EventImageService{

    private final EventImageRepository eventImageRepository;

    public void addEventImage(List<EventImageRequestDto> eventImageRequestDto, Long eventId) {
        List<EventImage> eventImages = eventImageRequestDto.stream()
                .map(dto -> EventImageRequestDto.toEntity(eventId,dto.getEventImageUrl(), dto.isThumbnail()))
                .toList();
        eventImageRepository.saveAll(eventImages);
        }

    public List<EventImageResponseDto> getEventImage(Long eventId) {
        return eventImageRepository.findByEventId(eventId)
                .stream()
                .map(EventImageResponseDto::from)
                .toList();
    }

    public EventImageResponseDto getEventThumbnailImage(Long eventId){
        EventImage eventImage = eventImageRepository.findByEventIdAndThumbnailTrue(eventId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EVENT_IMAGE_NOT_FOUND));
        return EventImageResponseDto.from(eventImage);

    }

    public void removeEventImage(Long eventImageId) {
        eventImageRepository.deleteById(eventImageId);
    }
}
