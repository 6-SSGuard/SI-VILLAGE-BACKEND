package org.example.sivillage.event.dto.in;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.event.domain.EventProduct;

@Getter
public class EventProductRequestDto {

private String productCode;

public static EventProduct toEntity(EventProductRequestDto eventProductRequestDto, Long eventId){
    return EventProduct.builder()
            .eventId(eventId)
            .productCode(eventProductRequestDto.getProductCode())
            .build();
}

@Builder
    public EventProductRequestDto(String productCode) {
        this.productCode = productCode;
    }

}
