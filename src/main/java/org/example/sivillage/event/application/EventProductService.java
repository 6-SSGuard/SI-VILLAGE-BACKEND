package org.example.sivillage.event.application;
import org.example.sivillage.event.dto.in.EventProductRequestDto;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;

import java.util.List;

public interface EventProductService {

    List<IdListResponseDto<String>> getEventProductCode(Long eventId); // 카테고리 코드 넣으면 그에 맞는 이벤트 id 값 던져주기
    void addEventProduct(Long eventId, EventProductRequestDto eventProductRequestDto);
    void removeEventProduct(String productCode);
}
