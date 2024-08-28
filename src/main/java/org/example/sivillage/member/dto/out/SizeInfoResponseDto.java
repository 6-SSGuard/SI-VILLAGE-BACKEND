package org.example.sivillage.member.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.member.domain.memberenum.BottomSize;
import org.example.sivillage.member.domain.memberenum.ShoeSize;
import org.example.sivillage.member.domain.memberenum.TopSize;

@Getter
@Builder
@AllArgsConstructor
public class SizeInfoResponseDto {

    private Integer height;

    private Integer weight;

    private TopSize topSize;

    private BottomSize bottomSize;

    private ShoeSize shoeSize;

    private String memberUuid;

}
