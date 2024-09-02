package org.example.sivillage.sizeinfo.dto.in;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
