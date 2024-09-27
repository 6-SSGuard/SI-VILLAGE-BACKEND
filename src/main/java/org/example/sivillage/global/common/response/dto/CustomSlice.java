package org.example.sivillage.global.common.response.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

public class CustomSlice<T> extends SliceImpl<T> {
    private final String lastValue;

    public CustomSlice(List<T> content, Pageable pageable, boolean hasNext, String lastValue) {
        super(content, pageable, hasNext);
        this.lastValue = lastValue;
    }

    public String getLastValue() {
        return lastValue;
    }
}
