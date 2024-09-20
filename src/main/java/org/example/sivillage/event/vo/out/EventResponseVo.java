package org.example.sivillage.event.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseVo {

    private String brand;

    private String eventTitle;

    private String eventContent;

    private List<String> promotionDetails;

}
