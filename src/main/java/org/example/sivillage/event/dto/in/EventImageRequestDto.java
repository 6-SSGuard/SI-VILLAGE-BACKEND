package org.example.sivillage.event.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.event.domain.EventImage;

@Getter
public class EventImageRequestDto {

    private String eventImageUrl;

    private boolean thumbnail;

    public static EventImage toEntity(Long eventId, String eventImageUrl, boolean thumbnail) {
        return EventImage.builder()
                .eventId(eventId)
                .eventImageUrl(eventImageUrl)
                .thumbnail(thumbnail)
                .build();
    }


    @Builder
    public EventImageRequestDto(String eventImageUrl, boolean thumbnail) {
        this.eventImageUrl = eventImageUrl;
        this.thumbnail = thumbnail;
    }

}

