package org.example.sivillage.event.infrastructure;

import org.example.sivillage.event.domain.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventImageRepository extends JpaRepository<EventImage,Long> {

    List<EventImage> findByEventId(Long eventId);
    Optional<EventImage> findByEventIdAndThumbnailTrue(Long eventId);
}
