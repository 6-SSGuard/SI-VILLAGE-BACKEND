package org.example.sivillage.event.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.event.domain.EventProduct;
import org.example.sivillage.event.dto.in.EventProductRequestDto;
import org.example.sivillage.event.infrastructure.EventProductRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional
public class EventProductServiceImpl implements EventProductService{

    private final EventProductRepository eventProductRepository;

    public List<IdListResponseDto<String>> getEventProductCode(Long eventId) {
        return eventProductRepository.findProductCodesByEventId(eventId)
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    public void addEventProduct(Long eventId, EventProductRequestDto eventProductRequestDto) {
        EventProduct eventProduct = eventProductRepository.save(EventProductRequestDto.toEntity(eventProductRequestDto,eventId));
    }

    public void removeEventProduct(String productCode) {
    eventProductRepository.findByProductCode(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PRODUCT));
    }
}
