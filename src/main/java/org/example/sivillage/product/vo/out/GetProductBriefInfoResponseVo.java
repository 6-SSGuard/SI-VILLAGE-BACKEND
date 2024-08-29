package org.example.sivillage.product.vo.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetProductBriefInfoResponseVo {
    private String productUuid;
    private String brandEngName;
    private String productName;
    private Integer price;
    private boolean isLiked;
}
