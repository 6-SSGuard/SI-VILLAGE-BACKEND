package org.example.sivillage.sizeinfo.vo.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SizeInfoResponseVo {

    private Integer height;
    private Integer weight;
    private String topSize;
    private String bottomSize;
    private String shoeSize;

}
