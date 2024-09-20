package org.example.sivillage.event.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String eventTitle;

    @Column(nullable = false)
    private String eventContent;

    private String promotionDetails;

    @Column(nullable = false)
    private String categoryCode;

    @Builder
    public Event(Long id, String memberUuid, String brand, String eventTitle, String eventContent, String promotionDetails, String categoryCode) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.brand = brand;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.promotionDetails = promotionDetails;
        this.categoryCode = categoryCode;
    }

}
