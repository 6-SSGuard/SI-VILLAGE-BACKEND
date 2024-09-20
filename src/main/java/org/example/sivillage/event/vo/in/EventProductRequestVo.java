package org.example.sivillage.event.vo.in;

import lombok.Getter;
import org.example.sivillage.event.dto.in.EventProductRequestDto;

@Getter
public class EventProductRequestVo {

private String productCode;

public EventProductRequestDto toDto(EventProductRequestVo eventProductRequestVo){
    return EventProductRequestDto.builder()
            .productCode(eventProductRequestVo.getProductCode())
            .build();
}
}
