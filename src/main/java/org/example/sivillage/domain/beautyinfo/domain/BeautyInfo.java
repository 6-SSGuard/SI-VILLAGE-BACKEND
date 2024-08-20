package org.example.sivillage.domain.beautyinfo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.domain.beautysizeinfo.domain.BeautySizeInfo;
import org.example.sivillage.domain.member.domain.Member;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BeautyKeyword beautyKeyword;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauty_size_info_id", nullable = false)
    private BeautySizeInfo beautySizeInfo;

}
