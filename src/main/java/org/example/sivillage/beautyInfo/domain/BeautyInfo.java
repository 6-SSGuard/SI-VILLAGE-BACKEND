package org.example.sivillage.beautyInfo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.global.common.BaseEntity;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BeautyInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String skinType;

    @Column(nullable = false)
    private String skinTone;

    @Column(nullable = false)
    private String scalpTone;

    @Column(nullable = false)
    private String beautyKeyword;

    @Column(nullable = false)
    private String memberUuid;

    @Builder
    public BeautyInfo(Long id, String skinType, String skinTone, String scalpTone, String beautyKeyword, String memberUuid) {
        this.id = id;
        this.skinType = skinType;
        this.skinTone = skinTone;
        this.scalpTone = scalpTone;
        this.beautyKeyword = beautyKeyword;
        this.memberUuid = memberUuid;
    }

}




