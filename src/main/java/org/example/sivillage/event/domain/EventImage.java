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
public class EventImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventImageUrl;

    @Column(nullable = false)
    private boolean thumbnail;

    @Column(nullable = false)
    private Long eventId;

    @Builder
    public EventImage(Long id, String eventImageUrl, Long eventId, boolean thumbnail) {
        this.id = id;
        this.eventImageUrl = eventImageUrl;
        this.eventId = eventId;
        this.thumbnail = thumbnail;
    }

}
