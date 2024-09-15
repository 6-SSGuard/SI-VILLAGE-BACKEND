package org.example.sivillage.admin.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetColorResponseVo {

    private Long colorId;
    private String colorName;
    private String colorCode;

    @Builder
    public GetColorResponseVo(Long colorId, String colorName, String colorCode) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
    }
}
