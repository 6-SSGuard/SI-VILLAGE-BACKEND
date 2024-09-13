package org.example.sivillage.sizeinfo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SizeInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    public SizeInfo(Long id, Integer height, Integer weight, String topSize, String bottomSize, String shoeSize, String memberUuid) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.topSize = topSize;
        this.bottomSize = bottomSize;
        this.shoeSize = shoeSize;
        this.memberUuid = memberUuid;
    }
}