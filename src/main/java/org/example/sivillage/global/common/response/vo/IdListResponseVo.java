package org.example.sivillage.global.common.response.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class IdListResponseVo<T> {

    private T id;

    @Builder
    public IdListResponseVo(T id) {
        this.id = id;
    }
}
