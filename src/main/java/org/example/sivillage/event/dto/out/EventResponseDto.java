package org.example.sivillage.event.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.event.domain.Event;
import org.example.sivillage.event.vo.out.EventResponseVo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class EventResponseDto {

    private String brand;

    private String eventTitle;

    private String eventContent;

    private List<String> promotionDetails;

    public static List<String> convertListPromotionDetails(String promotionDetails) {
        return Arrays.stream(promotionDetails.split("\\s*,\\s*"))
                .collect(Collectors.toList());
    }


    public static EventResponseDto from(Event event){
        return EventResponseDto.builder()
                .brand(event.getBrand())
                .eventTitle(event.getEventTitle())
                .eventContent(event.getEventContent())
                .promotionDetails(convertListPromotionDetails(event.getPromotionDetails()))
                .build();
    }

    public EventResponseVo toResponseVo(){
        return EventResponseVo.builder()
                .brand(brand)
                .eventTitle(eventTitle)
                .eventContent(eventContent)
                .promotionDetails(promotionDetails)
                .build();
    }


    @Builder
    public EventResponseDto(String brand, String eventTitle, String eventContent, List<String> promotionDetails) {
        this.brand = brand;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.promotionDetails = promotionDetails;
    }

}
