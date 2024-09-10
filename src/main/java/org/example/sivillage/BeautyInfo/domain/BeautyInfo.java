package org.example.sivillage.BeautyInfo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.BeautyInfo.domain.beautyenum.ScalpTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinTone;
import org.example.sivillage.BeautyInfo.domain.beautyenum.SkinType;
import org.example.sivillage.global.common.BaseEntity;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BeautyInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    private String memberUuid;

    @Builder
    public BeautyInfo(Long id, SkinType skinType, SkinTone skinTone, ScalpTone scalpTone, String beautyKeyword, String memberUuid) {
        this.id = id;
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
        this.memberUuid = memberUuid;
    }

}




