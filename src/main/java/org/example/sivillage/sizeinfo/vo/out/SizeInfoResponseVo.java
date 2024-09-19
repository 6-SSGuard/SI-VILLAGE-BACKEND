package org.example.sivillage.sizeinfo.vo.out;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeInfoResponseVo {

    private Integer height;
    private Integer weight;
    private String topSize;
    private String bottomSize;
    private String shoeSize;

}
