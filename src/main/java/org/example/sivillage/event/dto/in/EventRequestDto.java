    package org.example.sivillage.event.dto.in;
    import lombok.Builder;
    import lombok.Getter;
    import org.example.sivillage.event.domain.Event;


    @Getter
    public class EventRequestDto {

        private String brand;

        private String eventTitle;

        private String eventContent;

        private String promotionDetails;

        private String categoryCode;

        public static Event toEntity(EventRequestDto eventRequestDto, String memberUuid){
            return Event.builder()
                    .memberUuid(memberUuid)
                    .brand(eventRequestDto.getBrand())
                    .eventTitle(eventRequestDto.getEventTitle())
                    .eventContent(eventRequestDto.getEventContent())
                    .promotionDetails(eventRequestDto.getPromotionDetails())
                    .categoryCode(eventRequestDto.getCategoryCode())
                    .build();
        }

        public Event updateToEntity(EventRequestDto eventRequestDto, Event event){
            return Event.builder()
                    .id(event.getId())
                    .memberUuid(event.getMemberUuid())
                    .brand(eventRequestDto.getBrand())
                    .eventTitle(eventRequestDto.getEventTitle())
                    .eventContent(eventRequestDto.getEventContent())
                    .promotionDetails(eventRequestDto.getPromotionDetails())
                    .categoryCode(eventRequestDto.getCategoryCode())
                    .build();
        }

        @Builder
        public EventRequestDto(String brand, String eventTitle, String eventContent, String promotionDetails, String categoryCode){
            this.brand = brand;
            this.eventTitle = eventTitle;
            this.eventContent = eventContent;
            this.promotionDetails = promotionDetails;
            this.categoryCode = categoryCode;
        }

    }
