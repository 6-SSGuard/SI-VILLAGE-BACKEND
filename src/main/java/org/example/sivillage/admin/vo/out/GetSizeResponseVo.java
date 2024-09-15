package org.example.sivillage.admin.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetSizeResponseVo {

    private Long id;
    private String sizeName;
    private String sizeType;

    @Builder
    public GetSizeResponseVo(Long id, String sizeName, String sizeType) {
        this.id = id;
        this.sizeName = sizeName;
        this.sizeType = sizeType;
    }
}
