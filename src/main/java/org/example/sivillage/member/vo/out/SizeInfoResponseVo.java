package org.example.sivillage.member.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sivillage.member.domain.memberenum.BottomSize;
import org.example.sivillage.member.domain.memberenum.ShoeSize;
import org.example.sivillage.member.domain.memberenum.TopSize;

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
