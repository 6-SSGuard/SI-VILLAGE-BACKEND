package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.global.common.BaseEntity;
import org.example.sivillage.member.domain.memberenum.ScalpTone;
import org.example.sivillage.member.domain.memberenum.SkinTone;
import org.example.sivillage.member.domain.memberenum.SkinType;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeautyInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beauty_info_id")
    private Long beautyInfoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkinType skinType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkinTone skinTone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScalpTone scalpTone;

    @Column(nullable = false)
    private String beautyKeyword;

    @Column(nullable = false)
    private String memberUuid;

    @Builder
    public BeautyInfo(SkinType skinType, SkinTone skinTone, ScalpTone scalpTone, String beautyKeyword, String memberUuid) {
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
        this.memberUuid = memberUuid;
    }

    @Builder
    public static BeautyInfo toEntity (BeautyInfoRequestDto dto, String memberUuid) {
        return BeautyInfo.builder()
                .skinType(dto.getSkinType())
                .skinTone(dto.getSkinTone())
                .scalpTone(dto.getScalpTone())
                .beautyKeyword(dto.convertStringBeautyKeyword(dto.getBeautyKeyword()))
                .memberUuid(memberUuid)
                .build();
    }
    public void change(BeautyInfoRequestDto dto) {
        this.skinType = dto.getSkinType();
        this.skinTone = dto.getSkinTone();
        this.scalpTone = dto.getScalpTone();
        this.beautyKeyword = dto.convertStringBeautyKeyword(dto.getBeautyKeyword());
    }




}




