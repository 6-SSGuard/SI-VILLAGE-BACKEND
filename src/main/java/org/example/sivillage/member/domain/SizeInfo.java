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
@Builder
@AllArgsConstructor
public class SizeInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_info_id")
    private Long sizeInfoId;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopSize topSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BottomSize bottomSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShoeSize shoeSize;

    @Column(nullable = false)
    private String memberUuid;

    @Builder
    public static SizeInfo toEntity (SizeInfoRequestDto dto, String memberUuid) {
        return SizeInfo.builder()
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .topSize(dto.getTopSize())
                .bottomSize(dto.getBottomSize())
                .shoeSize(dto.getShoeSize())
                .memberUuid(memberUuid)
                .build();
    }
//
//    public void change(SizeInfoRequestDto dto) {
//        this.height = dto.getHeight();
//        this.weight = dto.getWeight();
//        this.topSize = dto.getTopSize();
//        this.bottomSize = dto.getBottomSize();
//        this.shoeSize = dto.getShoeSize();
//        this.memberUuid = dto.getMemberUuid();
//    }

}