package org.example.sivillage.memberinfo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.memberinfo.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.member.domain.Member;
import org.example.sivillage.global.common.BaseEntity;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeautyInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beauty_info_id")
    private Long id;

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

    @Column(nullable = false, name = "member_id")
    private Long memberId;

    @Builder
    public BeautyInfo(SkinType skinType, SkinTone skinTone, ScalpTone scalpTone, String beautyKeyword, Member member) {
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
        this.memberId = member.getId();
    }


    @Builder
    public static BeautyInfo toEntity (BeautyInfoRequestDto requestDto) {
        return BeautyInfo.builder()
                .skinType(requestDto.getSkinType())
                .skinTone(requestDto.getSkinTone())
                .scalpTone(requestDto.getScalpTone())
                .beautyKeyword(requestDto.getBeautyKeyword())
                .member(requestDto.getMember())
                .build();
    }

    }




