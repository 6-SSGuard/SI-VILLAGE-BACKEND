package org.example.sivillage.event.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.event.domain.EventImage;
import org.example.sivillage.event.vo.out.EventImageResponseVo;

@Getter
@NoArgsConstructor
public class EventImageResponseDto {

    private Long eventImageId;
    private String eventImageUrl;

    public static EventImageResponseDto from(EventImage eventImage) {
        return EventImageResponseDto.builder()
                .eventImageId(eventImage.getId())
                .eventImageUrl(eventImage.getEventImageUrl())
                .build();
    }

    public EventImageResponseVo toResponseVo() {
        return EventImageResponseVo.builder()
                .eventImageId(this.eventImageId)
                .eventImageUrl(this.eventImageUrl)
                .build();
    }

    @Builder
    public EventImageResponseDto(Long eventImageId, String eventImageUrl) {
        this.eventImageId = eventImageId;
        this.eventImageUrl = eventImageUrl;
    }
}
