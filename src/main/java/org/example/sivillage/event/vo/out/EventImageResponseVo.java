package org.example.sivillage.event.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.event.dto.out.EventImageResponseDto;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImageResponseVo {

    private Long eventImageId;
    private String eventImageUrl;

}