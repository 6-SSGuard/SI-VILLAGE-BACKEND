package org.example.sivillage.sizeinfo.dto.in;
import lombok.Builder;
import lombok.Getter;
import org.example.sivillage.sizeinfo.domain.SizeInfo;


@Getter
public class SizeInfoRequestDto {

    private Integer height;

    private Integer weight;

    private String topSize;

    private String bottomSize;

    private String shoeSize;

    public SizeInfo toEntity(SizeInfoRequestDto sizeInfoRequestDto, String memberUuid){
        return SizeInfo.builder()
                .height(sizeInfoRequestDto.getHeight())
                .weight(sizeInfoRequestDto.getWeight())
                .topSize(sizeInfoRequestDto.getTopSize())
                .bottomSize(sizeInfoRequestDto.getBottomSize())
                .shoeSize(sizeInfoRequestDto.getShoeSize())
                .memberUuid(memberUuid)
                .build();
    }

    public SizeInfo updateToEntity(SizeInfoRequestDto sizeInfoRequestDto, SizeInfo sizeInfo){
        return SizeInfo.builder()
                .id(sizeInfo.getId())
                .height(sizeInfoRequestDto.getHeight())
                .weight(sizeInfoRequestDto.getWeight())
                .topSize(sizeInfoRequestDto.getTopSize())
                .bottomSize(sizeInfoRequestDto.getBottomSize())
                .shoeSize(sizeInfoRequestDto.getShoeSize())
                .memberUuid(sizeInfo.getMemberUuid())
                .build();
    }


    @Builder
    public SizeInfoRequestDto(Integer height, Integer weight, String topSize, String bottomSize, String shoeSize){
        this.height = height;
        this.weight = weight;
        this.topSize = topSize;
        this.bottomSize = bottomSize;
        this.shoeSize = shoeSize;
    }

}
