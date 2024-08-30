package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.member.domain.memberenum.BottomSize;
import org.example.sivillage.member.domain.memberenum.ShoeSize;
import org.example.sivillage.member.domain.memberenum.TopSize;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.member.dto.in.SizeInfoRequestDto;

@Entity
@Getter // beauty 쪽으로 통합
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SizeInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_info_id")
    private Long sizeInfoId;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private String topSize;

    @Column(nullable = false)
    private String bottomSize;

    @Column(nullable = false)
    private String shoeSize;

    @Column(nullable = false)
    private String memberUuid;


    @Builder
    public SizeInfo(Integer height, Integer weight, String topSize, String bottomSize, String shoeSize, String memberUuid) {
        this.height = height;
        this.weight = weight;
        this.topSize = topSize;
        this.bottomSize = bottomSize;
        this.shoeSize = shoeSize;
        this.memberUuid = memberUuid;
    }


    @Builder
    public static SizeInfo toEntity (SizeInfoRequestDto dto, String memberUuid) {
        return SizeInfo.builder()
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .topSize(dto.getTopSize())
                .bottomSize(dto.getBottomSize())
                .shoeSize(dto.getShoeSize())
                .memberUuid(memberUuid).build();
    }

    public void change(SizeInfoRequestDto dto) {
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
        this.topSize = dto.getTopSize();
        this.bottomSize = dto.getBottomSize();
        this.shoeSize = dto.getShoeSize();
    }


}