package org.example.sivillage.sizeinfo.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.BeautyInfo.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.sizeinfo.domain.SizeInfo;
import org.example.sivillage.sizeinfo.dto.in.SizeInfoRequestDto;
import org.example.sivillage.sizeinfo.vo.out.SizeInfoResponseVo;

@Getter
public class SizeInfoResponseDto {

    private Integer height;

    private Integer weight;

    private String topSize;

    private String bottomSize;

    private String shoeSize;

    public static SizeInfoResponseDto from(SizeInfo sizeInfo) {
        return SizeInfoResponseDto.builder()
                .height(sizeInfo.getHeight())
                .weight(sizeInfo.getWeight())
                .topSize(sizeInfo.getTopSize())
                .bottomSize(sizeInfo.getBottomSize())
                .shoeSize(sizeInfo.getShoeSize())
                .build();
    }

    public static SizeInfoResponseDto emptyResponse() {
        return SizeInfoResponseDto.builder()
                .height(null)
                .weight(null)
                .topSize(null)
                .bottomSize(null)
                .shoeSize(null)
                .build();
    }

    public SizeInfoResponseVo toResponseVo() {
        return SizeInfoResponseVo.builder()
                .height(height)
                .weight(weight)
                .topSize(topSize)
                .bottomSize(bottomSize)
                .shoeSize(shoeSize)
                .build();
    }


    @Builder
    public SizeInfoResponseDto(Integer height, Integer weight, String topSize, String bottomSize, String shoeSize) {
        this.height = height;
        this.weight = weight;
        this.topSize = topSize;
        this.bottomSize = bottomSize;
        this.shoeSize = shoeSize;
    }
}
