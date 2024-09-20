package org.example.sivillage.event.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.event.dto.in.EventRequestDto;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestVo {

    @Schema(description = "브랜드", example = "BOBBI", required = true)
    @NotNull
    private String brand;

    @Schema(description = "이벤트 제목", example = "프리미엄 뷰티의 모든 것", required = true)
    @NotNull
    private String eventTitle;

    @Schema(description = "이벤트 상세내용", example = "UP TO 40%, 지금이 바로 뷰티 쇼핑 찬스", required = true)
    @NotNull
    private String eventContent;

    @Schema(description = "할인,쿠폰", example = "[\"신상품\", \"할인\", \"쿠폰\"]", required = true)
    private List<String> promotionDetails;

    @Schema(description = "카테고리", example = "", required = true)
    @NotNull
    private String categoryCode;

    public String convertStringPromotionDetails(List<String> promotionDetails) {
        return String.join(", ", promotionDetails);
    }

    public static EventRequestDto toDto(EventRequestVo eventRequestVo) {
        return EventRequestDto.builder()
                .brand(eventRequestVo.getBrand())
                .eventTitle(eventRequestVo.getEventTitle())
                .eventContent(eventRequestVo.getEventContent())
                .promotionDetails(eventRequestVo.convertStringPromotionDetails(eventRequestVo.getPromotionDetails()))
                .categoryCode(eventRequestVo.getCategoryCode())
                .build();
    }

}
