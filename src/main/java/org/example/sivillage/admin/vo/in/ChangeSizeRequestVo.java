package org.example.sivillage.admin.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeSizeRequestVo {

    private Long id;
    private String sizeName;
    private String sizeType;

    @Builder
    public ChangeSizeRequestVo(Long id, String sizeName, String sizeType) {
        this.id = id;
        this.sizeName = sizeName;
        this.sizeType = sizeType;
    }
}
