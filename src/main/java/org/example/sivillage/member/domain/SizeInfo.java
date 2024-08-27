package org.example.sivillage.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.member.domain.memberenum.BottomSize;
import org.example.sivillage.member.domain.memberenum.ShoeSize;
import org.example.sivillage.member.domain.memberenum.TopSize;
import org.example.sivillage.productoption.domain.Size;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter // beauty 쪽으로 통합
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class SizeInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_info_id")
    private Long id;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private int weight;

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
}