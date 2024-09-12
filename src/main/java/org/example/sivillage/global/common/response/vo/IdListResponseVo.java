package org.example.sivillage.global.common.response.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class IdListResponseVo {

    private Long id;

    @Builder
    public IdListResponseVo(Long id) {
        this.id = id;
    }
}
