package org.example.sivillage.domain.beautyinfo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.domain.beautyinfo.dto.BeautyInfoRequest;
import org.example.sivillage.domain.member.domain.Member;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public BeautyInfo(SkinType skinType, SkinTone skinTone, ScalpTone scalpTone, String beautyKeyword, Long id) {
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
    }
//
//    @Builder
//    public static BeautyInfo createBeautyInfo (BeautyInfoRequest request) {
//        return BeautyInfo.builder()
//                .skinType(request.getSkinType())
//                .skinTone(request.getSkinTone())
//                .scalpTone(request.getScalpTone())
//                .beautyKeyword(request.getBeautyKeyword())
//                .build();
//    }

    }




