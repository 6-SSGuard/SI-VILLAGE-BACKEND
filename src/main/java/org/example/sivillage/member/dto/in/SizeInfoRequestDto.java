package org.example.sivillage.member.dto.in;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.member.domain.memberenum.BottomSize;
import org.example.sivillage.member.domain.memberenum.ShoeSize;
import org.example.sivillage.member.domain.memberenum.TopSize;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeInfoRequestDto {

    private Integer height;

    private Integer weight;

    private String topSize;

    private String bottomSize;

    private String shoeSize;

}
