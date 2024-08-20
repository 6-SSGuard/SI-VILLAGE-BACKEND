package org.example.sivillage.domain.sizeinfo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.domain.beautysizeinfo.domain.BeautySizeInfo;
import org.example.sivillage.domain.product.domain.Size;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
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
    private Size topSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size pantsSize;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauty_size_info_id", nullable = false)
    private BeautySizeInfo beautySizeInfo;

}