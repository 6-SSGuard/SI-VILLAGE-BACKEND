package org.example.sivillage.sizeinfo.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.sizeinfo.domain.SizeInfo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeInfoResponseDto {

    private Integer height;

    private Integer weight;

    private String topSize;

    private String bottomSize;

    private String shoeSize;

    public static SizeInfoResponseDto toDto (SizeInfo sizeInfo) {
        return SizeInfoResponseDto.builder()
                .height(sizeInfo.getHeight())
                .weight(sizeInfo.getWeight())
                .topSize(sizeInfo.getTopSize())
                .bottomSize(sizeInfo.getBottomSize())
                .shoeSize(sizeInfo.getShoeSize())
                .build();
    }
}
