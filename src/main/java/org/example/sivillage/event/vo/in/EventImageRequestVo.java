package org.example.sivillage.event.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.example.sivillage.event.dto.in.EventImageRequestDto;

@Getter
public class EventImageRequestVo {


        @Schema(description = "이벤트 이미지 리스트", example = "https://cdn-mo.sivillage.com/mo/assets/comm/image/siv_logo.png", required = true)
        private String eventImageUrl;

        @Schema(description = "이벤트 이미지 썸네일", example = "true")
        private boolean thumbnail;

        public static EventImageRequestDto toDto(EventImageRequestVo eventImageRequestVo) {
            return EventImageRequestDto.builder()
                    .eventImageUrl(eventImageRequestVo.getEventImageUrl())
                    .thumbnail(eventImageRequestVo.isThumbnail())
                    .build();
        }
}
